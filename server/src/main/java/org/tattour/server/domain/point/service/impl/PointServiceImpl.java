package org.tattour.server.domain.point.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.admin.controller.dto.request.CancelPointChargeRequestReq;
import org.tattour.server.domain.custom.service.dto.request.GetCustomSummaryInfo;
import org.tattour.server.domain.custom.service.dto.response.CustomApplySummaryInfoList;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.domain.UserPointLog;
import org.tattour.server.domain.point.facade.dto.response.ReadPointChargeRequestListRes;
import org.tattour.server.domain.point.provider.impl.PointProviderImpl;
import org.tattour.server.domain.point.repository.impl.PointChargeRequestRepositoryImpl;
import org.tattour.server.domain.point.repository.impl.UserPointLogRepositoryImpl;
import org.tattour.server.domain.point.service.PointService;
import org.tattour.server.domain.point.service.dto.request.ConfirmPointChargeRequestDto;
import org.tattour.server.domain.point.service.dto.request.PatchPointChangeRequestReq;
import org.tattour.server.domain.point.service.dto.request.SavePointChargeRequestReq;
import org.tattour.server.domain.point.service.dto.response.ConfirmPointChargeResponseDto;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.dto.response.GetUserInfoDto;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.util.EntityDtoMapper;
import org.tattour.server.infra.discord.service.DiscordMessageService;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointChargeRequestRepositoryImpl pointChargeRequestRepository;
    private final UserPointLogRepositoryImpl userPointLogRepository;
    private final UserProviderImpl userProvider;
    private final UserServiceImpl userService;
    private final PointProviderImpl pointProvider;
    private final CustomProviderImpl customProvider;
    private final OrderProviderImpl orderProvider;
    private final DiscordMessageService discordMessageService;

    @Override
    @Transactional
    public void savePointChargeRequest(SavePointChargeRequestReq req) {
        User user = userProvider.getUserById(req.getUserId());
        PointChargeRequest pointChargeRequest = PointChargeRequest.of(req.getChargeAmount(), user);

        pointChargeRequestRepository.save(pointChargeRequest);
    }

    @Override
    @Transactional
    public void savePointLog(String title, String content, int amount, int resultPoint,
            int userId) {
        User user = userProvider.getUserById(userId);
        UserPointLog userPointLog =
                UserPointLog.of(
                        title,
                        content,
                        amount,
                        resultPoint,
                        user);
        userPointLogRepository.save(userPointLog);
        discordMessageService.sendPointChargeLogMessage(user, amount);
    }

    @Override
    @Transactional
    public void updatePointChargeRequest(PatchPointChangeRequestReq req) {
        PointChargeRequest pointChargeRequest = pointProvider.getPointChargeRequestById(
                req.getId());
        pointChargeRequest.setProperties(req.getTransferredAmount(), req.getIsDeposited(),
                req.getIsAmountMatched(), req.getIsApproved(), req.getIsCompleted());

        pointChargeRequestRepository.save(pointChargeRequest);
    }

    @Override
    @Transactional
    public ConfirmPointChargeResponseDto confirmPointChargeRequest(
            ConfirmPointChargeRequestDto req) {
        PointChargeRequest pointChargeRequest = pointProvider.getPointChargeRequestById(
                req.getId());

        if (!pointChargeRequest.getIsCompleted()) {
            // 처리된 요청이 아니면
            if (req.getTransferredAmount() == pointChargeRequest.getChargeAmount()) {
                // 송금된 값이 일치하면
                // PointChargeRequest의 상태를 변경하기
                updatePointChargeRequest(
                        PatchPointChangeRequestReq.of(req.getId(), req.getTransferredAmount(), true,
                                true, true, true));
                return null;
            } else {
                // 일치하지 않으면
                String baseDate = pointChargeRequest.getCreatedAt();

                // 유저 정보
                User user = userProvider.getUserById(req.getUserId());
                GetUserInfoDto getUserInfoDto = EntityDtoMapper.INSTANCE.toGetUserInfoDto(user);

                // 포인트 충전 내역
                ReadPointChargeRequestListRes readPointChargeRequestListRes =
                        pointProvider.getPointChargeRequestAfterDate(req.getUserId(), baseDate);
                readPointChargeRequestListRes
                        .getPointChargeRequestInfoList()
                        .add(0, EntityDtoMapper.INSTANCE.toGetPointChargeRequestRes(
                                pointChargeRequest));

                // 구매 내역
                ReadUserOrderHistoryListRes readUserOrderHistoryListRes =
                        orderProvider.readOrderHistoryAfterDate(
                                req.getUserId(),
                                baseDate);

                // 커스텀 신청내역
                CustomApplySummaryInfoList customApplySummaryInfoList =
                        customProvider.getCustomApplySummaryInfoList(
                                GetCustomSummaryInfo.of(req.getUserId(), baseDate));

                return ConfirmPointChargeResponseDto.of(getUserInfoDto,
                        readPointChargeRequestListRes, readUserOrderHistoryListRes,
                        customApplySummaryInfoList);
            }
        } else {
            // 이미 처리된 요청이면
            throw new BusinessException(ErrorType.ALREADY_COMPLETED_POINT_CHARGE_REQUEST_EXCEPTION);
        }

    }

    @Override
    @Transactional
    public void cancelPointChargeRequest(CancelPointChargeRequestReq req) {
        PointChargeRequest pointChargeRequest = pointProvider.getPointChargeRequestById(
                req.getId());

        if (pointChargeRequest.getIsCompleted()) {
            // 이미 처리된 요청이면 반려
            throw new BusinessException(ErrorType.ALREADY_COMPLETED_POINT_CHARGE_REQUEST_EXCEPTION);
        } else {
            if (Objects.equals(req.getTransferredAmount(), pointChargeRequest.getChargeAmount())) {
                // 송금 금액이 충전 금액이 같으면 반려
                throw new BusinessException(ErrorType.AMOUNT_MATCHED_EXCEPTION);
            } else {
                // PointChargeRequest의 상태를 변경하기
                updatePointChargeRequest(
                        PatchPointChangeRequestReq.of(req.getId(), req.getTransferredAmount(),
                                !Objects.isNull(req.getTransferredAmount()), false, false, true));

                // 포인트 로그 남기기
                User user = userProvider.getUserById(req.getUserId());
                int amount = pointChargeRequest.getChargeAmount();
                int resultPoint = user.getPoint() - amount;

                savePointLog("충전 취소", req.getReason(),
                        -pointChargeRequest.getChargeAmount(), resultPoint, user.getId());

                // 유저 포인트 처리
                userService.updateUserPoint(user.getId(), -amount);
            }
        }
    }
}




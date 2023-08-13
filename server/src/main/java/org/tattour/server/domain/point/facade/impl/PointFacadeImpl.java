package org.tattour.server.domain.point.facade.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.admin.controller.dto.request.CancelPointChargeRequestReq;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomSummaryListRes;
import org.tattour.server.domain.custom.provider.CustomProvider;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.order.provider.OrderProvider;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.domain.PointLogCategory;
import org.tattour.server.domain.point.domain.UserPointLog;
import org.tattour.server.domain.point.facade.PointFacade;
import org.tattour.server.domain.point.facade.dto.request.ConfirmPointChargeReq;
import org.tattour.server.domain.point.facade.dto.request.CreatePointChargeRequestReq;
import org.tattour.server.domain.point.facade.dto.request.ReadPointChargeRequestListReq;
import org.tattour.server.domain.point.facade.dto.request.ReadPointLogListReq;
import org.tattour.server.domain.point.facade.dto.response.ConfirmPointChargeRes;
import org.tattour.server.domain.point.facade.dto.response.ReadPointChargeRequestListRes;
import org.tattour.server.domain.point.facade.dto.response.ReadPointLogListRes;
import org.tattour.server.domain.point.provider.PointProvider;
import org.tattour.server.domain.point.service.PointService;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.UserProvider;
import org.tattour.server.domain.user.provider.vo.UserContactInfo;
import org.tattour.server.domain.user.service.UserService;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.util.EntityDtoMapper;
import org.tattour.server.infra.discord.service.DiscordMessageService;

@Service
@RequiredArgsConstructor
public class PointFacadeImpl implements PointFacade {

    private final PointService pointService;
    private final PointProvider pointProvider;
    private final UserService userService;
    private final UserProvider userProvider;
    private final CustomProvider customProvider;
    private final OrderProvider orderProvider;
    private final DiscordMessageService discordMessageService;


    @Override
    public void createPointChargeRequest(CreatePointChargeRequestReq req) {
        User user = userProvider.readUserById(req.getUserId());

        pointService.savePointChargeRequest(PointChargeRequest.of(user, req.getChargeAmount()));
        userService.updateUserPoint(user, req.getChargeAmount());
        pointService.savePointLog(
                UserPointLog.of(
                        PointLogCategory.REQUEST_CHARGE,
                        null,
                        req.getChargeAmount(),
                        user.getPoint(),
                        user));

        discordMessageService.sendPointChargeLogMessage(user, req.getChargeAmount());
    }

    @Override
    public ReadPointChargeRequestListRes readPointChargeRequest(ReadPointChargeRequestListReq req) {
        return pointProvider.readAllPointChargeRequest(req.getUserId(), req.getIsCompleted());
    }

    @Override
    public ConfirmPointChargeRes confirmPointChargeRequest(ConfirmPointChargeReq req) {
        PointChargeRequest pointChargeRequest = pointProvider.readPointChargeRequestById(
                req.getId());

        if (!pointChargeRequest.getIsCompleted()) {
            // 처리된 요청이 아니면
            if (req.getTransferredAmount() == pointChargeRequest.getChargeAmount()) {
                // 송금된 값이 일치하면
                // PointChargeRequest의 상태를 변경하기
                pointService.updatePointChargeRequest(pointChargeRequest,
                        req.getTransferredAmount(),
                        true, true, true, true);

                return null;
            } else {
                // 일치하지 않으면
                String baseDate = pointChargeRequest.getCreatedAt();

                // 유저 정보
                User user = userProvider.readUserById(req.getUserId());
                UserContactInfo userContactInfo = EntityDtoMapper.INSTANCE.toUserContactInfo(user);

                // 포인트 충전 내역
                ReadPointChargeRequestListRes readPointChargeRequestListRes =
                        pointProvider.readPointChargeRequestAfterDate(req.getUserId(), baseDate);
                readPointChargeRequestListRes
                        .getPointChargeRequestInfoList()
                        .add(0, EntityDtoMapper.INSTANCE.toGetPointChargeRequestRes(
                                pointChargeRequest));

                // 구매 내역
                ReadUserOrderHistoryListRes readUserOrderHistoryListRes =
                        ReadUserOrderHistoryListRes.of(
                                orderProvider.readOrderHistoryAfterDate(
                                        req.getUserId(),
                                        baseDate));

                // 커스텀 신청내역
                ReadCustomSummaryListRes readCustomSummaryListRes =
                        customProvider.readCustomSummaryInfoAfterDateByUserId(req.getUserId(), baseDate);

                return ConfirmPointChargeRes.of(userContactInfo,
                        readPointChargeRequestListRes,
                        readUserOrderHistoryListRes,
                        readCustomSummaryListRes);
            }
        } else {
            // 이미 처리된 요청이면
            throw new BusinessException(ErrorType.ALREADY_COMPLETED_POINT_CHARGE_REQUEST_EXCEPTION);
        }
    }

    @Override
    public void cancelPointChargeRequest(CancelPointChargeRequestReq req) {
        PointChargeRequest pointChargeRequest = pointProvider.readPointChargeRequestById(
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
                pointService.updatePointChargeRequest(
                        pointChargeRequest, req.getTransferredAmount(), true,
                        false, false, true);

                // 포인트 로그 남기기
                User user = userProvider.readUserById(req.getUserId());
                int amount = pointChargeRequest.getChargeAmount();
                int resultPoint = user.getPoint() - amount;

                pointService.savePointLog(
                        UserPointLog.of(
                                PointLogCategory.CANCEL_CHARGE,
                                req.getReason(),
                                -pointChargeRequest.getChargeAmount(),
                                resultPoint,
                                user));

                // 유저 포인트 처리
                userService.updateUserPoint(user, -amount);
            }
        }
    }

    @Override
    public ReadPointLogListRes readPointLog(ReadPointLogListReq req) {
        return ReadPointLogListRes.of(
                pointProvider.readPointLog(req.getUserId(), req.getCategory().name()));
    }
}

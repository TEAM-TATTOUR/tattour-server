package org.tattour.server.domain.point.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.domain.UserPointLog;
import org.tattour.server.domain.point.provider.impl.PointProviderImpl;
import org.tattour.server.domain.point.repository.impl.PointChargeRequestRepositoryImpl;
import org.tattour.server.domain.point.repository.impl.UserPointLogRepositoryImpl;
import org.tattour.server.domain.point.service.PointService;
import org.tattour.server.domain.point.service.dto.request.ConfirmPointChargeRequestDto;
import org.tattour.server.domain.point.service.dto.request.PatchPointChangeRequestReq;
import org.tattour.server.domain.point.service.dto.request.SavePointChargeRequestReq;
import org.tattour.server.domain.point.service.dto.request.SaveUserPointLogReq;
import org.tattour.server.domain.point.service.dto.response.ConfirmPointChargeResponseDto;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.dto.request.UpdateUserPointReq;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final PointChargeRequestRepositoryImpl pointChargeRequestRepository;
    private final UserPointLogRepositoryImpl userPointLogRepository;
    private final UserProviderImpl userProvider;
    private final UserServiceImpl userService;
    private final PointProviderImpl pointProvider;

    @Override
    @Transactional
    public void savePointChargeRequest(SavePointChargeRequestReq req) {
        User user = userProvider.getUserById(req.getUserId());
        PointChargeRequest pointChargeRequest = PointChargeRequest.of(req.getChargeAmount(), user);

        pointChargeRequestRepository.save(pointChargeRequest);
    }

    @Override
    @Transactional
    public void savePointLog(SaveUserPointLogReq req) {
        User user = userProvider.getUserById(req.getUserId());
        UserPointLog userPointLog =
                UserPointLog.of(
                        req.getTitle(),
                        req.getContent(),
                        req.getAmount(),
                        req.getResultPoint(),
                        user);

        userPointLogRepository.save(userPointLog);
    }

    @Override
    @Transactional
    public void updatePointChargeRequest(PatchPointChangeRequestReq req) {
        PointChargeRequest pointChargeRequest = pointProvider.getPointChargeRequestById(req.getId());
        pointChargeRequest.setProperties(req.getTransferredAmount(), req.getIsDeposited(), req.getIsAmountMatched(), req.getIsApproved(), req.getIsCompleted());
    }

    @Override
    @Transactional
    public ConfirmPointChargeResponseDto confirmPointChargeRequest(ConfirmPointChargeRequestDto req) {
        PointChargeRequest pointChargeRequest = pointProvider.getPointChargeRequestById(req.getId());

        if(req.getTransferredAmount() == pointChargeRequest.getChargeAmount()) {
            // 송금된 값이 일치하면
            // PointChargeRequest의 상태를 변경하기
            updatePointChargeRequest(
                    PatchPointChangeRequestReq.of(req.getId(), req.getTransferredAmount(),true, true, true, true));
        } else {
            // 일치하지 않으면


//            // PointChargeRequest의 상태를 변경하기
//            updatePointChargeRequest(
//                    PatchPointChangeRequestReq.of(req.getId(), req.getTransferredAmount(),false, false, false, true));
//
//            // 포인트 로그 남기기
//            User user = userProvider.getUserById(pointChargeRequest.getUser().getId());
//            int amount = pointChargeRequest.getChargeAmount();
//            int resultPoint = user.getPoint() - amount;
//
//            savePointLog(SaveUserPointLogReq.of("충전 취소", req.getReason(), -pointChargeRequest.getChargeAmount(), resultPoint, user.getId()));
//
//            // 유저 포인트 처리
//            userService.updateUserPoint(UpdateUserPointReq.of(user.getId(), -amount));
        }
    }
}

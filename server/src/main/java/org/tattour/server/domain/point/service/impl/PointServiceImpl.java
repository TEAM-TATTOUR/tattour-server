package org.tattour.server.domain.point.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.domain.UserPointLog;
import org.tattour.server.domain.point.provider.impl.PointProviderImpl;
import org.tattour.server.domain.point.repository.impl.PointChargeRequestRepositoryImpl;
import org.tattour.server.domain.point.repository.impl.UserPointLogRepositoryImpl;
import org.tattour.server.domain.point.service.PointService;
import org.tattour.server.domain.point.service.dto.request.ConfirmPointChargeRequestReq;
import org.tattour.server.domain.point.service.dto.request.PatchPointChangeRequestReq;
import org.tattour.server.domain.point.service.dto.request.SavePointChargeRequestReq;
import org.tattour.server.domain.point.service.dto.request.SaveUserPointLogReq;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final PointChargeRequestRepositoryImpl pointChargeRequestRepository;
    private final UserPointLogRepositoryImpl userPointLogRepository;
    private final UserProviderImpl userProvider;
    private final PointProviderImpl pointProvider;

    @Override
    public void savePointChargeRequest(SavePointChargeRequestReq req) {
        User user = userProvider.getUserById(req.getUserId());
        PointChargeRequest pointChargeRequest = PointChargeRequest.of(req.getChargeAmount(), user);

        pointChargeRequestRepository.save(pointChargeRequest);
    }

    @Override
    public void savePointLog(SaveUserPointLogReq req) {
        User user = userProvider.getUserById(req.getUserId());
        UserPointLog userPointLog = UserPointLog.of(req.getContent(), req.getAmount(),
                req.getResultPoint(), user);

        userPointLogRepository.save(userPointLog);
    }

    @Override
    public void updatePointChargeRequest(PatchPointChangeRequestReq req) {
        PointChargeRequest pointChargeRequest = pointProvider.getPointChargeRequestById(req.getId());
        pointChargeRequest.setConditions(req.getIsDeposited(), req.getIsAmountMatched(), req.getIsApproved(), req.getIsCompleted());
    }

    @Override
    public void confirmPointChargeRequest(ConfirmPointChargeRequestReq req) {
        if(req.isApproved()){
            // 승인되면
            // PointChargeRequest의 상태를 변경하기(is_deposited, is_amount_matched, is_approved:true, is_completed:true)
            updatePointChargeRequest(PatchPointChangeRequestReq.of(req.getId(), true, true, true, true));
            // 포인트 로그 남기기
        } else {
            // 승인 안되면
            // ointChargeRequest의 상태를 변경하기(is_deposited, is_amount_matched, is_approved:false, is_completed:true)
            // 포인트 로그 남기기
            // 유저 포인트 처리
        }
    }
}

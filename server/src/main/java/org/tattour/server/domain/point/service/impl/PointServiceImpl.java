package org.tattour.server.domain.point.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.admin.controller.dto.request.CancelPointChargeRequestReq;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.domain.PointLogCategory;
import org.tattour.server.domain.point.domain.UserPointLog;
import org.tattour.server.domain.point.provider.impl.PointProviderImpl;
import org.tattour.server.domain.point.repository.impl.PointChargeRequestRepositoryImpl;
import org.tattour.server.domain.point.repository.impl.UserPointLogRepositoryImpl;
import org.tattour.server.domain.point.service.PointService;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

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
    public void savePointChargeRequest(PointChargeRequest pointChargeRequest) {
        pointChargeRequestRepository.save(pointChargeRequest);
    }

    @Override
    @Transactional
    public void savePointLog(UserPointLog userPointLog) {
        userPointLogRepository.save(userPointLog);
    }

    @Override
    @Transactional
    public void updatePointChargeRequest(PointChargeRequest pointChargeRequest,
            int transferredAmount,
            boolean isDeposited, boolean isAmountMatched, boolean isApproved, boolean isCompleted) {
        pointChargeRequest.setProperties(transferredAmount, isDeposited, isAmountMatched,
                isApproved, isCompleted);

        pointChargeRequestRepository.save(pointChargeRequest);
    }
}




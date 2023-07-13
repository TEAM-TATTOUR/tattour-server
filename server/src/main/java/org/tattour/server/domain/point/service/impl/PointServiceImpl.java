package org.tattour.server.domain.point.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.domain.UserPointLog;
import org.tattour.server.domain.point.repository.impl.PointChargeRequestRepositoryImpl;
import org.tattour.server.domain.point.repository.impl.UserPointLogRepositoryImpl;
import org.tattour.server.domain.point.service.PointService;
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
}

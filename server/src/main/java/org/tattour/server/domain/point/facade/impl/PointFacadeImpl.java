package org.tattour.server.domain.point.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.point.facade.PointFacade;
import org.tattour.server.domain.point.facade.dto.request.CreatePointChargeRequestReq;
import org.tattour.server.domain.point.service.impl.PointServiceImpl;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;

@Service
@RequiredArgsConstructor
public class PointFacadeImpl implements PointFacade {
    private final PointServiceImpl pointService;
    private final UserServiceImpl userService;

    @Override
    public void createPointChargeRequest(CreatePointChargeRequestReq req) {
        pointService.createPointChargeRequest(req.getUserId(), req.getChargeAmount());

        int resultPoint = userService.updateUserPoint(req.getUserId(), req.getChargeAmount());
        pointService.createPointLog(
                "포인트 충전 요청",
                null,
                req.getChargeAmount(),
                resultPoint,
                req.getUserId());
    }
}

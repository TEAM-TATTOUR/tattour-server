package org.tattour.server.domain.point.facade;

import org.tattour.server.domain.point.facade.dto.request.CreatePointChargeRequestReq;

public interface PointFacade {
    // 포인트 충전 요청
    void createPointChargeRequest(CreatePointChargeRequestReq req);
}

package org.tattour.server.domain.point.service;

import org.tattour.server.domain.point.service.dto.request.SavePointChargeRequestReq;
import org.tattour.server.domain.point.service.dto.request.SaveUserPointLogReq;

public interface PointService {
    // 포인트 충전 요청 저장하기
    void savePointChargeRequest (SavePointChargeRequestReq req);

    // 포인트 로그 저장하기
    void savePointLog(SaveUserPointLogReq req);
}

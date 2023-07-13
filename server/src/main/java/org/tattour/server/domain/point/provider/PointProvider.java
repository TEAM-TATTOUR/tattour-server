package org.tattour.server.domain.point.provider;

import org.tattour.server.domain.point.provider.dto.response.GetPointChargeRequestListRes;

public interface PointProvider {
    // 조건에 따라 포인트 충전 요청 가져오기
    GetPointChargeRequestListRes getAllPointChargeRequest(Integer userId, Boolean isComplete);

    // 조건에 따라 포인트 로그 가져오기
}
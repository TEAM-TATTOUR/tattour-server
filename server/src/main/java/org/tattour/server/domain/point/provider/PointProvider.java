package org.tattour.server.domain.point.provider;

import org.tattour.server.domain.point.provider.dto.response.GetPointChargeRequestListRes;
import org.tattour.server.domain.point.provider.dto.response.GetUserPointChargeRequestListRes;

public interface PointProvider {
    // 전체 포인트 충전 요청 가져오기
    GetPointChargeRequestListRes getAllPointChargeRequest();

    // 유저 포인트 충전 요청 가져오기
    GetUserPointChargeRequestListRes getUserPointChargeRequest();

    // 유저 포인트 로그 가져오기
}

package org.tattour.server.domain.point.provider;

import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.facade.dto.response.ReadPointChargeRequestListRes;
import org.tattour.server.domain.point.facade.dto.response.ReadPointLogListRes;

public interface PointProvider {

    // id로 포인트 충전 요청 가져오기
    PointChargeRequest getPointChargeRequestById(Integer id);

    // id와 날짜로 포인트 충전 요청 가져오기
    ReadPointChargeRequestListRes getPointChargeRequestAfterDate(int userId, String date);

    // 조건에 따라 포인트 충전 요청 가져오기
    ReadPointChargeRequestListRes getAllPointChargeRequest(Integer userId, Boolean isComplete);

    // 조건에 따라 포인트 로그 가져오기
    ReadPointLogListRes getPointLog(Integer userId, String title);
}

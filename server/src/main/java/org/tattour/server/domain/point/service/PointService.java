package org.tattour.server.domain.point.service;

import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.domain.UserPointLog;

public interface PointService {

    // 포인트 충전 요청 저장하기
    void savePointChargeRequest(PointChargeRequest pointChargeRequest);

    // 포인트 로그 저장하기
    void savePointLog(UserPointLog userPointLog);

    // 포인트 충전 요청 수정하기
    // TODO : 삭제하기
    void updatePointChargeRequest(PointChargeRequest pointChargeRequest, int transferredAmount,
            boolean isDeposited, boolean isAmountMatched, boolean isApproved, boolean isCompleted);
}

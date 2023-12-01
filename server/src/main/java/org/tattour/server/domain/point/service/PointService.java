package org.tattour.server.domain.point.service;

import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.domain.UserPointLog;

public interface PointService {

    void savePointChargeRequest(PointChargeRequest pointChargeRequest);

    void savePointLog(UserPointLog userPointLog);

    // TODO : 삭제하기
    void updatePointChargeRequest(PointChargeRequest pointChargeRequest, int transferredAmount,
            boolean isDeposited, boolean isAmountMatched, boolean isApproved, boolean isCompleted);
}

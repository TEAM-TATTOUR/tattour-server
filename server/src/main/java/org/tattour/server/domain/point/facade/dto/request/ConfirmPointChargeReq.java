package org.tattour.server.domain.point.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPointChargeReq {
    private int id;
    private int userId;
    private int transferredAmount;

    public static ConfirmPointChargeReq of(int id, int userId, int transferredAmount) {
        return new ConfirmPointChargeReq(id, userId, transferredAmount);
    }
}

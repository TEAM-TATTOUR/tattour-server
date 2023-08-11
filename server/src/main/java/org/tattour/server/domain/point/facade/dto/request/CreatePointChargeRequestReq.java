package org.tattour.server.domain.point.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePointChargeRequestReq {

    private int userId;
    private int chargeAmount;

    public static CreatePointChargeRequestReq of(int userId, int chargeAmount) {
        return new CreatePointChargeRequestReq(userId, chargeAmount);
    }
}

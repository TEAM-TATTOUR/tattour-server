package org.tattour.server.domain.point.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SavePointChargeRequestReq {
    private int userId;
    private int chargeAmount;

    public static SavePointChargeRequestReq of(int userId, int chargeAmount){
        return new SavePointChargeRequestReq(userId, chargeAmount);
    }
}

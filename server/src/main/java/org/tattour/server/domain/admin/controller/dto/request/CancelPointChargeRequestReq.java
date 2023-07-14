package org.tattour.server.domain.admin.controller.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CancelPointChargeRequestReq {

    private int id;
    private int userId;
    private Integer transferredAmount;
    private String reason;

    public static CancelPointChargeRequestReq of(int id, int userId, Integer transferredAmount, String reason) {
        return new CancelPointChargeRequestReq(id, userId, transferredAmount, reason);
    }
}

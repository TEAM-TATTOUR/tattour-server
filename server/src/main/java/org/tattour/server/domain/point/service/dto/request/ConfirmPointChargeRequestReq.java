package org.tattour.server.domain.point.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPointChargeRequestReq {
    private int id;
    private boolean isApproved;

    public static ConfirmPointChargeRequestReq of(int id, boolean isApproved) {
        return new ConfirmPointChargeRequestReq(id, isApproved);
    }
}

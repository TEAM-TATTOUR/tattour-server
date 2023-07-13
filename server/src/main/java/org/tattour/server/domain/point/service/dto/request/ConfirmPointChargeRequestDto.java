package org.tattour.server.domain.point.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPointChargeRequestDto {
    private int id;
    private int transferredAmount;
    private boolean isApproved;
    private String reason;

    public static ConfirmPointChargeRequestDto of(int id, int transferredAmount, boolean isApproved, String reason) {
        return new ConfirmPointChargeRequestDto(id, transferredAmount, isApproved, reason);
    }
}

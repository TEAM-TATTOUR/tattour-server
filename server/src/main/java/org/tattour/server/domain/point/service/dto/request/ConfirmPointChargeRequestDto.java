package org.tattour.server.domain.point.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPointChargeRequestDto {
    private int id;
    private int userId;
    private int transferredAmount;
    private String reason;

    public static ConfirmPointChargeRequestDto of(int id, int userId, int transferredAmount, String reason) {
        return new ConfirmPointChargeRequestDto(id, userId, transferredAmount,reason);
    }
}

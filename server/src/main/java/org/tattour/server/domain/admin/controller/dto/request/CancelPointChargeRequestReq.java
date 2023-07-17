package org.tattour.server.domain.admin.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.global.config.resolver.DivisibleBy;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CancelPointChargeRequestReq {

    @NotNull(message = "id is null")
    private Integer id;

    @NotNull(message = "userId is null")
    private Integer userId;

    @DivisibleBy
    @NotNull(message = "transferredAmount is null")
    private Integer transferredAmount;

    @NotBlank(message = "reason is required")
    private String reason;

    public static CancelPointChargeRequestReq of(int id, int userId, Integer transferredAmount, String reason) {
        return new CancelPointChargeRequestReq(id, userId, transferredAmount, reason);
    }
}

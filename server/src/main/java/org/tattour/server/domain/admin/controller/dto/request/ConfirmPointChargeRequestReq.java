package org.tattour.server.domain.admin.controller.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.global.config.resolver.DivisibleBy;

@Getter
@NoArgsConstructor
public class ConfirmPointChargeRequestReq {
    @NotNull(message = "id is null")
    private Integer id;

    @NotNull(message = "userId is null")
    private Integer userId;

    @DivisibleBy
    @NotNull(message = "transferredAmount is null")
    private Integer transferredAmount;
}

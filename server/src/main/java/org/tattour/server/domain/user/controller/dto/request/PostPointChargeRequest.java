package org.tattour.server.domain.user.controller.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.tattour.server.global.config.resolver.DivisibleBy;

@Getter
public class PostPointChargeRequest {
    @DivisibleBy()
    @NotNull(message = "chargeAmount is null")
    @Min(value = 1000)
    private Integer chargeAmount;
}

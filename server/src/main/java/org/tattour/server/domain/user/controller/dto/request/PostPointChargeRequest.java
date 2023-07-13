package org.tattour.server.domain.user.controller.dto.request;

import javax.validation.constraints.Min;
import lombok.Getter;
import org.tattour.server.global.config.resolver.DivisibleBy;

@Getter
public class PostPointChargeRequest {
    private Integer userId;
    @DivisibleBy
    @Min(value = 1000)
    private Integer chargeAmount;
}

package org.tattour.server.domain.user.controller.dto.request;

import lombok.Getter;
import org.tattour.server.global.config.resolver.DivisibleBy;

@Getter
public class PostPointChargeRequest {
    private Integer userId;
    @DivisibleBy
    private Integer chargeAmount;
}

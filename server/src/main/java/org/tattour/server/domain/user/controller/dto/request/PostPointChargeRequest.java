package org.tattour.server.domain.user.controller.dto.request;

import lombok.Getter;
import org.tattour.server.global.config.resolver.DivisibleBy;

@Getter
public class PostPointChargeRequest {
    private Integer userId;
    // TODO : 1000원으로 나누어떨어지는지 확인
    @DivisibleBy
    private Integer chargeAmount;
}

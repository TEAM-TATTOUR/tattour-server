package org.tattour.server.domain.user.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.tattour.server.global.config.annotations.DivisibleBy;

@Schema(description = "포인트 신청 요청 Request")
@Getter
public class PostPointChargeRequest {

    @Schema(description = "충전 금액", example = "5000")
    @DivisibleBy()
    @NotNull(message = "chargeAmount is null")
    @Min(value = 1000)
    private Integer chargeAmount;
}

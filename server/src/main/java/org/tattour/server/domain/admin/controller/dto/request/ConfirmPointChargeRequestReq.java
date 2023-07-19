package org.tattour.server.domain.admin.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.global.config.resolver.DivisibleBy;

@Schema(description = "포인트 충전 요청 확정 Request")
@Getter
@NoArgsConstructor
public class ConfirmPointChargeRequestReq {
    @Schema(description = "포인트 충전 요청 id")
    @NotNull(message = "id is null")
    private Integer id;

    @Schema(description = "user id")
    @NotNull(message = "userId is null")
    private Integer userId;

    @Schema(description = "송금받은 금액", example = "3000")
    @DivisibleBy
    @NotNull(message = "transferredAmount is null")
    private Integer transferredAmount;
}

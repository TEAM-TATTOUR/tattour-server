package org.tattour.server.domain.order.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.tattour.server.domain.order.domain.OrderStatus;

@Schema(description = "주문상태 갱신 Request (PREPARATION / CANCEL / ACCEPT / DELIVERING / DELIVERED)")
@Getter
public class PatchOrderStatusReq {

    @Schema(description = "주문상태", example = "ACCEPT")
    @NotNull(message = "orderStatus is null")
    private OrderStatus orderStatus;
}

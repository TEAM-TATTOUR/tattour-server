package org.tattour.server.domain.order.controller.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.tattour.server.domain.order.domain.OrderStatus;

@Getter
public class PatchOrderStatusReq {
    @NotNull(message = "orderStatus is null")
    private OrderStatus orderStatus;
}

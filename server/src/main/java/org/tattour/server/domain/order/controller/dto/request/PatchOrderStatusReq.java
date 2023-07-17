package org.tattour.server.domain.order.controller.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.tattour.server.domain.order.domain.OrderStatus;

@Getter
public class PatchOrderStatusReq {
    @NotBlank(message = "orderStatus is required")
    private OrderStatus orderStatus;
}

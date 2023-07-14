package org.tattour.server.domain.order.controller.dto.request;

import lombok.Getter;
import org.tattour.server.domain.order.domain.OrderStatus;

@Getter
public class PatchOrderStatusReq {
    private OrderStatus orderStatus;
}

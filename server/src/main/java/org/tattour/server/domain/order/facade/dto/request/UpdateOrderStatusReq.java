package org.tattour.server.domain.order.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.model.OrderStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateOrderStatusReq {

    private int orderId;
    private OrderStatus orderStatus;

    public static UpdateOrderStatusReq of(int orderId, OrderStatus orderStatus) {
        return new UpdateOrderStatusReq(orderId, orderStatus);
    }
}

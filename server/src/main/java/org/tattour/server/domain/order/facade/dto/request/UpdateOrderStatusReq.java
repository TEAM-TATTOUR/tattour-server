package org.tattour.server.domain.order.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.domain.OrderStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateOrderStatusReq {

    private int id;
    private OrderStatus orderStatus;

    public static UpdateOrderStatusReq of(int id, OrderStatus orderStatus) {
        return new UpdateOrderStatusReq(id, orderStatus);
    }
}

package org.tattour.server.domain.order.service.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
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

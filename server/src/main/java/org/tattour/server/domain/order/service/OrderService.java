package org.tattour.server.domain.order.service;

import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.domain.OrderStatus;
import org.tattour.server.domain.order.facade.dto.request.CreateOrderRequest;
import org.tattour.server.domain.order.facade.dto.request.UpdateOrderStatusReq;

public interface OrderService {

    // 결제하기
    Order saveOrder(Order order);
}

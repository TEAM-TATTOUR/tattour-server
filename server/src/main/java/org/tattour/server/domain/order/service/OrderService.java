package org.tattour.server.domain.order.service;

import org.tattour.server.domain.order.domain.Order;

public interface OrderService {

    // 결제하기
    Order saveOrder(Order order);
}

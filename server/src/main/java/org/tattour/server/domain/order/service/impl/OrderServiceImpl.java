package org.tattour.server.domain.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.order.controller.dto.request.PostOrderReq;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.repository.impl.OrderRepositoryImpl;
import org.tattour.server.domain.order.service.OrderService;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepositoryImpl orderRepository;

    @Override
    @Transactional
    public void saveOrder(PostOrderReq req) {
//        Order
//        orderRepository.save();
    }
}

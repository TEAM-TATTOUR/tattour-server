package org.tattour.server.domain.order.service;

import org.tattour.server.domain.order.controller.dto.request.PostOrderReq;

public interface OrderService {
    // 결제하기
    void saveOrder(PostOrderReq req);
}

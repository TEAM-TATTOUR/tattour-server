package org.tattour.server.domain.order.service;

import org.tattour.server.domain.order.controller.dto.request.PostOrderReq;
import org.tattour.server.domain.order.service.dto.request.UpdateOrderStatusReq;

public interface OrderService {
    // 결제하기
    void saveOrder(PostOrderReq req);

    // 결제 내역 상태 변경
    void updateOrderStatus(UpdateOrderStatusReq req);
}

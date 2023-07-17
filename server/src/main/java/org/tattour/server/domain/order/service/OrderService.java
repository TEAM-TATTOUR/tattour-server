package org.tattour.server.domain.order.service;

import org.tattour.server.domain.order.service.dto.request.PostOrderReqDto;
import org.tattour.server.domain.order.service.dto.request.UpdateOrderStatusReq;

public interface OrderService {
    // 결제하기
    void saveOrder(PostOrderReqDto req);

    // 결제 내역 상태 변경
    void updateOrderStatus(UpdateOrderStatusReq req);
}

package org.tattour.server.domain.order.service;

import org.tattour.server.domain.order.service.dto.request.PostOrderReq;
import org.tattour.server.domain.order.service.dto.response.PostOrderRes;

public interface OrderService {
    // 결제하기
    PostOrderRes order(PostOrderReq postOrderReq);
}

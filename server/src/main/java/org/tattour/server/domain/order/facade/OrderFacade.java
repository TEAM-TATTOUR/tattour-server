package org.tattour.server.domain.order.facade;

import org.tattour.server.domain.order.controller.dto.response.ReadOrderSheetRes;
import org.tattour.server.domain.order.model.PurchaseRequest;
import org.tattour.server.domain.order.facade.dto.request.CreateOrderReq;
import org.tattour.server.domain.order.facade.dto.request.UpdateOrderStatusReq;
import org.tattour.server.domain.order.facade.dto.response.ReadOrderHistoryListRes;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;

public interface OrderFacade {
    ReadOrderSheetRes readOrderSheet(int userId, PurchaseRequest purchaseRequest);

    void order(PurchaseRequest purchaseRequest, CreateOrderReq createOrderReq);

    ReadUserOrderHistoryListRes readOrderHistoryByUserId(Integer userId);

    ReadOrderHistoryListRes readOrderHistoryOnPage(int page);

    void updateOrderStatus(UpdateOrderStatusReq req);
}

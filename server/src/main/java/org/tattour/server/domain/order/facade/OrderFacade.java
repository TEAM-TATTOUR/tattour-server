package org.tattour.server.domain.order.facade;

import org.tattour.server.domain.order.controller.dto.response.ReadOrderSheetRes;
import org.tattour.server.domain.order.facade.dto.request.CreateOrderRequest;
import org.tattour.server.domain.order.facade.dto.request.ReadOrderSheetReq;
import org.tattour.server.domain.order.facade.dto.request.UpdateOrderStatusReq;
import org.tattour.server.domain.order.facade.dto.response.ReadOrderHistoryListRes;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;

public interface OrderFacade {
    // 결제 시트 불러오기
    ReadOrderSheetRes readOrderSheet(ReadOrderSheetReq req);

    // 주문하기
    void createOrder(CreateOrderRequest req);

    // 유저 결제 내역 불러오기
    ReadUserOrderHistoryListRes readOrderHistoryByUserId(Integer userId);

    // 모든 결제내역 불러오기
    ReadOrderHistoryListRes readOrderHistoryOnPage(int page);

    // 주문내역 상태 변경
    void updateOrderStatus(UpdateOrderStatusReq req);
}

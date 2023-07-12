package org.tattour.server.domain.order.provider;

import org.tattour.server.domain.order.controller.dto.request.GetOrderSheetReq;
import org.tattour.server.domain.order.controller.dto.response.GetOrderSheetRes;
import org.tattour.server.domain.order.provider.dto.response.GetUserOrderHistoryListRes;

public interface OrderProvider {
    // 결제 페이지 불러오기
    GetOrderSheetRes getOrderSheetRes(GetOrderSheetReq req);

    // 결제 내역 불러오기
    GetUserOrderHistoryListRes getUserOrderHistory(Integer userId);
}

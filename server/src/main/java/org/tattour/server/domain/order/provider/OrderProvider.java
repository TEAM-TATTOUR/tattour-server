package org.tattour.server.domain.order.provider;

import org.tattour.server.domain.order.controller.dto.request.GetOrderSheetReq;
import org.tattour.server.domain.order.controller.dto.response.GetOrderSheetRes;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.provider.dto.request.GetOrderHistoryAfterDateReq;
import org.tattour.server.domain.order.provider.dto.request.GetOrderSheetReqDto;
import org.tattour.server.domain.order.provider.dto.response.GetOrderHistoryListRes;
import org.tattour.server.domain.order.provider.dto.response.GetUserOrderHistoryListRes;

public interface OrderProvider {

    // 결제 내역 1개 가져오기
    Order getOrderById(int id);

    // 결제 페이지 불러오기
    GetOrderSheetRes getOrderSheetRes(GetOrderSheetReqDto req);

    // 페이지로 결제 내역 불러오기
    GetOrderHistoryListRes getOrderHistoryByPage(int page);

    // 유저 결제 내역 불러오기
    GetUserOrderHistoryListRes getOrderHistoryByUserId(Integer userId);

    // 기준날짜로 결제 내역 불러오기
    GetUserOrderHistoryListRes getOrderHistoryAfterDate(GetOrderHistoryAfterDateReq req);
}

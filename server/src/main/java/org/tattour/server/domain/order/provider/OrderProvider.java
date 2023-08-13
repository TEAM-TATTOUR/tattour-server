package org.tattour.server.domain.order.provider;

import java.util.List;
import org.springframework.data.domain.Page;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.provider.vo.OrderAmountInfo;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.order.provider.vo.UserOrderHistoryInfo;

public interface OrderProvider {

    // 결제 내역 1개 가져오기
    Order readOrderById(int orderId);

    // 페이지로 결제 내역 불러오기
    Page<Order> readOrderHistoryByPage(int page);

    // 유저 결제 내역 불러오기
    ReadUserOrderHistoryListRes readOrderHistoryByUserId(int userId);

    // 기준날짜로 결제 내역 불러오기
    List<UserOrderHistoryInfo> readOrderHistoryAfterDate(int userId, String date);

    // 결제 금액정보 가져오기
    OrderAmountInfo readOrderAmountRes(int price, int count, int shippingFee);

    // 상품 금액 계산
    int calculateProductAmount(int price, int count);

    // 총 결제금액 계산
    int calculateTotalAmount(int productAmount, int shippingFee);
}

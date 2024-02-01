package org.tattour.server.domain.order.provider;

import java.util.List;
import org.springframework.data.domain.Page;
import org.tattour.server.domain.order.model.OrderHistory;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.order.provider.vo.UserOrderHistoryInfo;

public interface OrderProvider {

    OrderHistory readOrderHistoryById(int orderHistoryId);

    Page<OrderHistory> readOrderHistoryByPage(int page);

    ReadUserOrderHistoryListRes readOrderHistoryByUserId(int userId);

    List<UserOrderHistoryInfo> readOrderHistoryAfterDate(int userId, String date);
}

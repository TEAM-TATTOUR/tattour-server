package org.tattour.server.domain.order.facade.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.domain.order.provider.vo.OrderHistoryInfo;
import org.tattour.server.domain.order.provider.vo.OrderHistoryPageInfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadOrderHistoryListRes {

    private List<OrderHistoryInfo> orderHistoryInfoList;
    private OrderHistoryPageInfo orderHistoryPageInfo;

    public static ReadOrderHistoryListRes of(List<OrderHistoryInfo> orderHistoryInfoList,
            OrderHistoryPageInfo orderHistoryPageInfo) {
        return new ReadOrderHistoryListRes(orderHistoryInfoList, orderHistoryPageInfo);
    }
}

package org.tattour.server.domain.order.provider.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderHistoryAfterDateReq {
    Integer userId;
    String date;

    public static GetOrderHistoryAfterDateReq of(Integer userId, String date) {
        return new GetOrderHistoryAfterDateReq(userId, date);
    }
}

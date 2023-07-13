package org.tattour.server.domain.order.provider.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderHistoryListRes {
    List<GetOrderHistoryRes> getOrderHistoryResList;
    private PageInfoRes pageInfoRes;

    public static GetOrderHistoryListRes of(List<GetOrderHistoryRes> getOrderHistoryResList, PageInfoRes pageInfoRes){
        return new GetOrderHistoryListRes(getOrderHistoryResList, pageInfoRes);
    }
}

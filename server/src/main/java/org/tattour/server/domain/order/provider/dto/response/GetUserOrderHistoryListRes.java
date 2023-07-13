package org.tattour.server.domain.order.provider.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserOrderHistoryListRes {
    List<GetUserOrderHistoryRes> getUserOrderHistoryResList;

    public static GetUserOrderHistoryListRes of(List<GetUserOrderHistoryRes> getUserOrderHistoryResList){
        return new GetUserOrderHistoryListRes(getUserOrderHistoryResList);
    }
}

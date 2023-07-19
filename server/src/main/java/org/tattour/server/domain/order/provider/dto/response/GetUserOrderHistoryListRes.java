package org.tattour.server.domain.order.provider.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "user 결제 내역 리스트 Response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserOrderHistoryListRes {

    List<GetUserOrderHistoryRes> getUserOrderHistoryResList;

    public static GetUserOrderHistoryListRes of(
            List<GetUserOrderHistoryRes> getUserOrderHistoryResList) {
        return new GetUserOrderHistoryListRes(getUserOrderHistoryResList);
    }
}

package org.tattour.server.domain.order.facade.dto.response;

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
public class ReadUserOrderHistoryListRes {

    List<ReadUserOrderHistoryRes> readUserOrderHistoryResList;

    public static ReadUserOrderHistoryListRes of(
            List<ReadUserOrderHistoryRes> readUserOrderHistoryResList) {
        return new ReadUserOrderHistoryListRes(readUserOrderHistoryResList);
    }
}

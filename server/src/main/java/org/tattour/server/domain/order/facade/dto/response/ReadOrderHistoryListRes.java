package org.tattour.server.domain.order.facade.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadOrderHistoryListRes {

    List<ReadOrderHistoryRes> readOrderHistoryResList;
    private PageInfoRes pageInfoRes;

    public static ReadOrderHistoryListRes of(List<ReadOrderHistoryRes> readOrderHistoryResList,
            PageInfoRes pageInfoRes) {
        return new ReadOrderHistoryListRes(readOrderHistoryResList, pageInfoRes);
    }
}

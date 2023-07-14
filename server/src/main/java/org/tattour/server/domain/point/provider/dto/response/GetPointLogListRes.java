package org.tattour.server.domain.point.provider.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPointLogListRes {
    List<GetPointLogRes> getPointLogResList;

    public static GetPointLogListRes of(List<GetPointLogRes> getPointLogResList){
        return new GetPointLogListRes(getPointLogResList);
    }
}

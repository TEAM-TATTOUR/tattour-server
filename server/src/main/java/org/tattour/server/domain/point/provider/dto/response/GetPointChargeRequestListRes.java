package org.tattour.server.domain.point.provider.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPointChargeRequestListRes {
    List<GetPointChargeRequestRes> getPointChargeRequestResList;

    public static GetPointChargeRequestListRes of(List<GetPointChargeRequestRes> getPointChargeRequestResList){
        return new GetPointChargeRequestListRes(getPointChargeRequestResList);
    }
}

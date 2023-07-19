package org.tattour.server.domain.point.provider.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "포인트 충전 요청 내역 리스트 Response")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPointChargeRequestListRes {

    List<GetPointChargeRequestRes> getPointChargeRequestResList;

    public static GetPointChargeRequestListRes of(
            List<GetPointChargeRequestRes> getPointChargeRequestResList) {
        return new GetPointChargeRequestListRes(getPointChargeRequestResList);
    }
}

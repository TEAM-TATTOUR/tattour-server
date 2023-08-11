package org.tattour.server.domain.point.facade.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.point.provider.vo.PointChargeRequestInfo;

@Schema(description = "포인트 충전 요청 내역 리스트 Response")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadPointChargeRequestListRes {

    List<PointChargeRequestInfo> pointChargeRequestInfoList;

    public static ReadPointChargeRequestListRes of(
            List<PointChargeRequestInfo> pointChargeRequestInfoList) {
        return new ReadPointChargeRequestListRes(pointChargeRequestInfoList);
    }
}

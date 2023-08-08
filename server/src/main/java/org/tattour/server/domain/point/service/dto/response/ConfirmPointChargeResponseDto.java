package org.tattour.server.domain.point.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.custom.service.dto.response.CustomApplySummaryInfoList;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.point.provider.dto.response.GetPointChargeRequestListRes;
import org.tattour.server.domain.user.provider.dto.response.GetUserInfoDto;

@Schema(description = "송금 금액이 다를 경우 user 정보 Response")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPointChargeResponseDto {

    // 유저 정보
    GetUserInfoDto getUserInfoDto;
    // 포인트 충전 내역
    GetPointChargeRequestListRes getPointChargeRequestListRes;
    // 구매 내역
    ReadUserOrderHistoryListRes orderHistoryListRes;
    // 커스텀 신청내역
    CustomApplySummaryInfoList customApplySummaryInfoList;

    public static ConfirmPointChargeResponseDto of(
            GetUserInfoDto getUserInfoDto,
            GetPointChargeRequestListRes getPointChargeRequestListRes,
            ReadUserOrderHistoryListRes readUserOrderHistoryListRes,
            CustomApplySummaryInfoList customApplySummaryInfoList) {
        return new ConfirmPointChargeResponseDto(getUserInfoDto, getPointChargeRequestListRes,
                readUserOrderHistoryListRes, customApplySummaryInfoList);
    }
}

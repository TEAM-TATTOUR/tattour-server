package org.tattour.server.domain.point.service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.custom.service.dto.response.CustomApplySummaryInfoList;
import org.tattour.server.domain.order.provider.dto.response.GetUserOrderHistoryListRes;
import org.tattour.server.domain.point.provider.dto.response.GetPointChargeRequestListRes;
import org.tattour.server.domain.user.provider.dto.response.GetUserInfoDto;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPointChargeResponseDto {
    // 유저 정보
    GetUserInfoDto getUserInfoDto;
    // 포인트 충전 내역
    GetPointChargeRequestListRes getPointChargeRequestListRes;
    // 구매 내역
    GetUserOrderHistoryListRes orderHistoryListRes;
    // 커스텀 신청내역
    CustomApplySummaryInfoList customApplySummaryInfoList;

    public static ConfirmPointChargeResponseDto of(GetUserInfoDto getUserInfoDto,
            GetPointChargeRequestListRes getPointChargeRequestListRes,
            GetUserOrderHistoryListRes getUserOrderHistoryListRes,
            CustomApplySummaryInfoList customApplySummaryInfoList) {
        return new ConfirmPointChargeResponseDto(getUserInfoDto, getPointChargeRequestListRes,
                getUserOrderHistoryListRes, customApplySummaryInfoList);
    }
}

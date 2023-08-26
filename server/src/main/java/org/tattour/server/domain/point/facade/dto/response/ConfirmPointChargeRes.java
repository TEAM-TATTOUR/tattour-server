package org.tattour.server.domain.point.facade.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomSummaryListRes;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.user.provider.vo.UserContactInfo;

@Schema(description = "송금 금액이 다를 경우 user 정보 Response")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ConfirmPointChargeRes {

    // 유저 정보
    UserContactInfo userContactInfo;
    // 포인트 충전 내역
    ReadPointChargeRequestListRes readPointChargeRequestListRes;
    // 구매 내역
    ReadUserOrderHistoryListRes orderHistoryListRes;
    // 커스텀 신청내역
    ReadCustomSummaryListRes readCustomSummaryListRes;

    public static ConfirmPointChargeRes of(
            UserContactInfo userContactInfo,
            ReadPointChargeRequestListRes readPointChargeRequestListRes,
            ReadUserOrderHistoryListRes readUserOrderHistoryListRes,
            ReadCustomSummaryListRes readCustomSummaryListRes) {
        return new ConfirmPointChargeRes(userContactInfo, readPointChargeRequestListRes,
                readUserOrderHistoryListRes, readCustomSummaryListRes);
    }
}

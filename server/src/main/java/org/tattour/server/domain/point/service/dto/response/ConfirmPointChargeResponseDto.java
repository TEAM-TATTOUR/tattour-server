package org.tattour.server.domain.point.service.dto.response;

import org.tattour.server.domain.order.provider.dto.response.GetOrderHistoryListRes;
import org.tattour.server.domain.user.provider.dto.response.GetUserInfoDto;

public class ConfirmPointChargeResponseDto {
    GetUserInfoDto getUserInfoDto;
    GetOrderHistoryListRes orderHistoryListRes;
    // 커스텀 신청내역
}

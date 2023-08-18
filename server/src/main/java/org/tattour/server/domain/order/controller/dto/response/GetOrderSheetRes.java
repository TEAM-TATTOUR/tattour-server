package org.tattour.server.domain.order.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.provider.dto.response.GetOrderAmountRes;
import org.tattour.server.domain.order.provider.dto.response.GetUserOrderPointRes;
import org.tattour.server.domain.sticker.provider.dto.response.GetOrderSheetStickerInfo;
import org.tattour.server.domain.user.provider.vo.UserProfileInfo;

@Schema(description = "결제 페이지 불러오기")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderSheetRes {
    private UserProfileInfo userProfileInfo;

    private GetOrderSheetStickerInfo getOrderSheetStickerInfo;
    
    private GetOrderAmountRes getOrderAmountRes;
    
    private GetUserOrderPointRes getUserOrderPointRes;

    public static GetOrderSheetRes of(
            UserProfileInfo userProfileInfo,
            GetOrderSheetStickerInfo getOrderSheetStickerInfo,
            GetOrderAmountRes getOrderAmountRes,
            GetUserOrderPointRes getUserOrderPointRes) {
        return new GetOrderSheetRes(userProfileInfo, getOrderSheetStickerInfo, getOrderAmountRes, getUserOrderPointRes);
    }
}

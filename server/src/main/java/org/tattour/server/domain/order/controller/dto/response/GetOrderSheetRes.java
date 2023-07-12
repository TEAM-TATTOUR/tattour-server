package org.tattour.server.domain.order.controller.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.provider.dto.response.GetOrderAmountRes;
import org.tattour.server.domain.order.provider.dto.response.GetUserOrderPointRes;
import org.tattour.server.domain.sticker.provider.dto.response.GetOrderSheetStickerInfo;
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderSheetRes {
    private GetOrderSheetStickerInfo getOrderSheetStickerInfo;
    private GetOrderAmountRes getOrderAmountRes;
    private GetUserOrderPointRes getUserOrderPointRes;

    public static GetOrderSheetRes of(
            GetOrderSheetStickerInfo getOrderSheetStickerInfo,
            GetOrderAmountRes getOrderAmountRes,
            GetUserOrderPointRes getUserOrderPointRes) {
        return new GetOrderSheetRes(getOrderSheetStickerInfo, getOrderAmountRes, getUserOrderPointRes);
    }
}

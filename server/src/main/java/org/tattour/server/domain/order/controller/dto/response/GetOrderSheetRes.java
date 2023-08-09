package org.tattour.server.domain.order.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.provider.dto.response.GetOrderAmountRes;
import org.tattour.server.domain.order.provider.dto.response.GetUserOrderPointRes;
import org.tattour.server.domain.sticker.provider.dto.response.ReadOrderSheetStickerRes;
@Schema(description = "결제 페이지 불러오기")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderSheetRes {
    private ReadOrderSheetStickerRes readOrderSheetStickerRes;
    
    private GetOrderAmountRes getOrderAmountRes;
    
    private GetUserOrderPointRes getUserOrderPointRes;

    public static GetOrderSheetRes of(
            ReadOrderSheetStickerRes readOrderSheetStickerRes,
            GetOrderAmountRes getOrderAmountRes,
            GetUserOrderPointRes getUserOrderPointRes) {
        return new GetOrderSheetRes(readOrderSheetStickerRes, getOrderAmountRes, getUserOrderPointRes);
    }
}

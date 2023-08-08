package org.tattour.server.domain.order.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.facade.dto.response.ReadOrderAmountRes;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderPointRes;
import org.tattour.server.domain.sticker.provider.dto.response.ReadOrderSheetStickerInfo;
@Schema(description = "결제 페이지 불러오기")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetOrderSheetRes {
    private ReadOrderSheetStickerInfo readOrderSheetStickerInfo;
    
    private ReadOrderAmountRes readOrderAmountRes;
    
    private ReadUserOrderPointRes readUserOrderPointRes;

    public static GetOrderSheetRes of(
            ReadOrderSheetStickerInfo readOrderSheetStickerInfo,
            ReadOrderAmountRes readOrderAmountRes,
            ReadUserOrderPointRes readUserOrderPointRes) {
        return new GetOrderSheetRes(readOrderSheetStickerInfo, readOrderAmountRes,
                readUserOrderPointRes);
    }
}

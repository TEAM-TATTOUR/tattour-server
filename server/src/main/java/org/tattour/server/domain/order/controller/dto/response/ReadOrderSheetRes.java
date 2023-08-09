package org.tattour.server.domain.order.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.provider.vo.OrderAmountInfo;
import org.tattour.server.domain.user.provider.vo.UserPointAfterOrderInfo;
import org.tattour.server.domain.sticker.provider.vo.ReadOrderSheetStickerInfo;
@Schema(description = "결제 페이지 불러오기")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadOrderSheetRes {
    private ReadOrderSheetStickerInfo readOrderSheetStickerInfo;
    
    private OrderAmountInfo orderAmountInfo;
    
    private UserPointAfterOrderInfo userPointAfterOrderInfo;

    public static ReadOrderSheetRes of(
            ReadOrderSheetStickerInfo readOrderSheetStickerInfo,
            OrderAmountInfo orderAmountInfo,
            UserPointAfterOrderInfo userPointAfterOrderInfo) {
        return new ReadOrderSheetRes(readOrderSheetStickerInfo, orderAmountInfo,
                userPointAfterOrderInfo);
    }
}

package org.tattour.server.domain.order.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.provider.vo.OrderAmountInfo;
import org.tattour.server.domain.sticker.provider.vo.ReadOrderSheetStickerInfo;
import org.tattour.server.domain.user.provider.vo.UserProfileInfo;

@Schema(description = "결제 페이지 불러오기")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadOrderSheetRes {
    private UserProfileInfo userProfileInfo;
    private ReadOrderSheetStickerInfo readOrderSheetStickerInfo;
    private OrderAmountInfo orderAmountInfo;

    public static ReadOrderSheetRes of(
            UserProfileInfo userProfileInfo,
            ReadOrderSheetStickerInfo readOrderSheetStickerInfo,
            OrderAmountInfo orderAmountInfo) {
        return new ReadOrderSheetRes(userProfileInfo, readOrderSheetStickerInfo, orderAmountInfo);
    }
}

package org.tattour.server.domain.sticker.provider;

import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.dto.response.GetOrderSheetStickerInfo;

public interface StickerProvider {

    // 스티커 엔티티 가져오기
    Sticker getStickerById(Integer stickerId);

    // 결제 시트에서 스티커 정보 가져오기
    GetOrderSheetStickerInfo getOrderSheetStickerInfo(Integer stickerId);
}

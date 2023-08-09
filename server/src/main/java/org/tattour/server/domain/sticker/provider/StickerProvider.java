package org.tattour.server.domain.sticker.provider;

import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.vo.ReadOrderSheetStickerInfo;

public interface StickerProvider {

    // 스티커 엔티티 가져오기
    Sticker getStickerById(Integer stickerId);

    // 결제 시트에서 스티커 정보 가져오기
    ReadOrderSheetStickerInfo readOrderSheetStickerInfo(Integer stickerId);
}

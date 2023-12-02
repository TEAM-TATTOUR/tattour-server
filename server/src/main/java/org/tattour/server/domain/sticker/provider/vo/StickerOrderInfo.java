package org.tattour.server.domain.sticker.provider.vo;

import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.sticker.domain.Sticker;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StickerOrderInfo {
    private Map<Sticker, Integer> stickerOrderInfos;

    public static StickerOrderInfo of(Map<Sticker, Integer> stickerOrderInfo) {
        return new StickerOrderInfo(stickerOrderInfo);
    }
}

package org.tattour.server.domain.sticker.provider.vo;

import java.util.Map;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.sticker.model.Sticker;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StickerOrderInfo {

    private Map<Sticker, Integer> stickerOrderInfos;

    public static StickerOrderInfo of(Map<Sticker, Integer> stickerOrderInfo) {
        return new StickerOrderInfo(stickerOrderInfo);
    }

    public int calculateProductAmount() {
        return stickerOrderInfos
                .entrySet()
                .stream()
                .mapToInt(entry -> getFinalProductPrice(entry.getKey()) * entry.getValue())
                .sum();
    }

    private int getFinalProductPrice(Sticker sticker) {
        return Objects.isNull(sticker.getDiscountPrice())
                ? sticker.getPrice() : sticker.getDiscountPrice();
    }

    public int calculateTotalAmount(int shippingFee) {
        return calculateProductAmount() + shippingFee;
    }
}

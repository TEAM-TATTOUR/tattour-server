package org.tattour.server.domain.sticker.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tattour.server.domain.sticker.exception.NotFoundStickerSortException;

@Getter
@RequiredArgsConstructor
public enum StickerSort {
    POPULARITY("popularity"),
    PRICE_LOW("price_low"),
    PRICE_HIGH("price_high");

    private final String value;

    public static StickerSort getStickerSort(String value) {
        switch (value) {
            case "popularity":
                return StickerSort.POPULARITY;
            case "price_low":
                return StickerSort.PRICE_LOW;
            case "price_high":
                return StickerSort.PRICE_HIGH;
            default:
                throw new NotFoundStickerSortException();
        }
    }
}

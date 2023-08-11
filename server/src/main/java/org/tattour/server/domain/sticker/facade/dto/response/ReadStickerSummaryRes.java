package org.tattour.server.domain.sticker.facade.dto.response;

import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.sticker.domain.Sticker;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReadStickerSummaryRes {

    private Integer id;
    private String name;
    private String imageUrl;
    private Integer price;
    private Integer discountRate;
    private Integer discountPrice;
    private Boolean isCustom;

    public static ReadStickerSummaryRes from(Sticker sticker) {
        Integer discountRate = null;
        Integer discountPrice = null;
        if (!Objects.isNull(sticker.getDiscount())) {
            discountRate = sticker.getDiscount().getDiscountRate();
            discountPrice = sticker.getDiscountPrice();
        }
        return ReadStickerSummaryRes.builder()
                .id(sticker.getId())
                .name(sticker.getName())
                .imageUrl(sticker.getMainImageUrl())
                .price(sticker.getPrice())
                .discountRate(discountRate)
                .discountPrice(discountPrice)
                .isCustom(sticker.getIsCustom())
                .build();
    }
}

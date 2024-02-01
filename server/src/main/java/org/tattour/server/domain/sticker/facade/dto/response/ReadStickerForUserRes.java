package org.tattour.server.domain.sticker.facade.dto.response;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.sticker.model.Sticker;
import org.tattour.server.domain.sticker.model.StickerImage;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ReadStickerForUserRes {

    private Integer id;
    private String name;
    private String description;
    private Integer price;
    private Integer discountRate;
    private Integer discountPrice;
    private String composition;
    private String size;
    private Boolean isCustom;
    private Integer shippingCost;
    private List<String> stickerThemes;
    private List<String> stickerStyles;
    private String mainImage;
    private List<String> detailImages;
    private Boolean productLiked;


    public static ReadStickerForUserRes of(Sticker sticker, Boolean productLiked) {
        Integer discountRate = null;
        Integer discountPrice = null;

        if (!Objects.isNull(sticker.getDiscount())) {
            discountRate = sticker.getDiscount().getDiscountRate();
            discountPrice = sticker.getDiscountPrice();
        }

        List<String> stickerImages = sticker.getStickerImages()
                .stream()
                .map(StickerImage::getImageUrl)
                .collect(Collectors.toList());

        List<String> stickerThemes = sticker.getStickerThemes()
                .stream()
                .map(theme -> theme.getTheme().getName())
                .collect(Collectors.toList());

        List<String> stickerStyles = sticker.getStickerStyles()
                .stream()
                .map(style -> style.getStyle().getName())
                .collect(Collectors.toList());

        return ReadStickerForUserRes.builder()
                .id(sticker.getId())
                .name(sticker.getName())
                .description(sticker.getDescription())
                .price(sticker.getPrice())
                .discountRate(discountRate)
                .discountPrice(discountPrice)
                .composition(sticker.getComposition())
                .size(sticker.getSize())
                .isCustom(sticker.getIsCustom())
                .shippingCost(3000)
                .stickerThemes(stickerThemes)
                .stickerStyles(stickerStyles)
                .mainImage(sticker.getMainImageUrl())
                .detailImages(stickerImages)
                .productLiked(productLiked)
                .build();
    }
}

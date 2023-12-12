package org.tattour.server.domain.sticker.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerForUserRes;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class GetStickerForUserRes {

    @Schema(description = "스티커 아이디")
    private Integer id;

    @Schema(description = "스티커 이름")
    private String name;

    @Schema(description = "스티커 설명")
    private String description;

    @Schema(description = "스티커 가격")
    private Integer price;

    @Schema(description = "할인률", nullable = true)
    private Integer discountRate;

    @Schema(description = "할인된 가격", nullable = true)
    private Integer discountPrice;

    @Schema(description = "스티커 구성")
    private String composition;

    @Schema(description = "스티커 크기")
    private String size;

    @Schema(description = "커스텀 스티커 여부")
    private Boolean isCustom;

    @Schema(description = "배송비")
    private Integer shippingCost;

    @Schema(description = "스티커 테마")
    private List<String> stickerThemes;

    @Schema(description = "스티커 스타일")
    private List<String> stickerStyles;

    @Schema(description = "메인 이미지 url")
    private String mainImage;

    @Schema(description = "상세 이미지 url 목록")
    private List<String> detailImages;

    @Schema(description = "상품 좋아요 여부")
    private Boolean productLiked;


    public static GetStickerForUserRes from(ReadStickerForUserRes readStickerForUserRes) {
        return GetStickerForUserRes.builder()
                .id(readStickerForUserRes.getId())
                .name(readStickerForUserRes.getName())
                .description(readStickerForUserRes.getDescription())
                .price(readStickerForUserRes.getPrice())
                .discountRate(readStickerForUserRes.getDiscountRate())
                .discountPrice(readStickerForUserRes.getDiscountPrice())
                .composition(readStickerForUserRes.getComposition())
                .size(readStickerForUserRes.getSize())
                .isCustom(readStickerForUserRes.getIsCustom())
                .shippingCost(readStickerForUserRes.getShippingCost())
                .stickerThemes(readStickerForUserRes.getStickerThemes())
                .stickerStyles(readStickerForUserRes.getStickerStyles())
                .mainImage(readStickerForUserRes.getMainImage())
                .detailImages(readStickerForUserRes.getDetailImages())
                .productLiked(readStickerForUserRes.getProductLiked())
                .build();
    }
}

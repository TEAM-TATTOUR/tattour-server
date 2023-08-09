package org.tattour.server.domain.sticker.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerSummaryRes;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class GetStickerSummaryRes {

    @Schema(description = "스티커 아이디")
    private Integer id;

    @Schema(description = "스티커 이름")
    private String name;

    @Schema(description = "스티커 이미지")
    private String imageUrl;

    @Schema(description = "스티커 가격")
    private Integer price;

    @Schema(description = "할인률", nullable = true)
    private Integer discountRate;

    @Schema(description = "할인된 가격", nullable = true)
    private Integer discountPrice;

    @Schema(description = "커스텀 스티커 여부")
    private Boolean isCustom;

    public static GetStickerSummaryRes from(ReadStickerSummaryRes readStickerSummaryRes) {
        return GetStickerSummaryRes.builder()
                .id(readStickerSummaryRes.getId())
                .name(readStickerSummaryRes.getName())
                .imageUrl(readStickerSummaryRes.getImageUrl())
                .price(readStickerSummaryRes.getPrice())
                .discountRate(readStickerSummaryRes.getDiscountRate())
                .discountPrice(readStickerSummaryRes.getDiscountPrice())
                .isCustom(readStickerSummaryRes.getIsCustom())
                .build();
    }
}

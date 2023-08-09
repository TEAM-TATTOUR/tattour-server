package org.tattour.server.domain.sticker.provider.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Todo: 리펙토링 필요
@Getter
@Setter
@AllArgsConstructor
public class StickerLikedInfo {

    @Schema(description = "좋아요 누른 타투 스티커 id")
    private Integer id;

    @Schema(description = "타투 스티커 id")
    private Integer stickerId;

    @Schema(description = "타투 스티커 이름", example = "포효하는 호랑이")
    private String name;

    @Schema(description = "가격", example = "3500")
    private Integer price;

    @Schema(description = "할인률", example = "10")
    private Integer discountRate;

    @Schema(description = "할인 가격", example = "3150")
    private Integer discountPrice;

    @Schema(description = "메인 이미지 url", example = "https://tattoo170829.wisacdn.com/data/file/tattoo_posting/833046566_5d1d5fc1db100.jpg")
    private String mainImageUrl;
}

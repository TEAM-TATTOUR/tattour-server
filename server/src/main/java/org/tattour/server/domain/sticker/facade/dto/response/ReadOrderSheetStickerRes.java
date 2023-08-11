package org.tattour.server.domain.sticker.facade.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "타투 스티커 정보")
@Getter
@Setter
public class ReadOrderSheetStickerRes {

    @Schema(description = "메인 배너 이미지", example = "https://tattoo170829.wisacdn.com/data/file/tattoo_posting/833046566_5d1d5fc1db100.jpg")
    private String mainImageUrl;

    @Schema(description = "타투 스티커 이름", example = "포효하는 호랑이")
    private String name;

    @Schema(description = "가격", example = "3500")
    private Integer price;

    @Schema(description = "할인된 가격", example = "3150")
    private Integer discountedPrice;

    @Schema(description = "상품 개수", example = "3")
    private Integer count;

    private ReadOrderSheetStickerRes(String mainImageUrl, String name, Integer price,
            Integer discountedPrice) {
        this.mainImageUrl = mainImageUrl;
        this.name = name;
        this.price = price;
        this.discountedPrice = discountedPrice;
    }

    public static ReadOrderSheetStickerRes of(String mainImageUrl, String name, Integer price,
            Integer discountedPrice) {
        return new ReadOrderSheetStickerRes(mainImageUrl, name, price, discountedPrice);
    }

    public void setCount(int count) {
        this.count = count;
    }
}

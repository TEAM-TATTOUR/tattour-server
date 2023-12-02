package org.tattour.server.domain.order.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Todo: 리펙토링 필요
@Schema(description = "타투 스티커 정보")
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderSheetStickerRes {

    @Schema(description = "메인 배너 이미지", example = "https://tattoo170829.wisacdn.com/data/file/tattoo_posting/833046566_5d1d5fc1db100.jpg")
    private String mainImageUrl;

    @Schema(description = "타투 스티커 이름", example = "포효하는 호랑이")
    private String name;

    @Schema(description = "가격", example = "3500")
    private Integer price;

    @Schema(description = "할인된 가격", example = "3150")
    private Integer discountPrice;

    @Schema(description = "상품 개수", example = "3")
    private Integer count;

    public static OrderSheetStickerRes of(
            String mainImageUrl,
            String name,
            int price,
            Integer discountPrice,
            int count) {
        return new OrderSheetStickerRes(mainImageUrl, name, price, discountPrice, count);
    }

    public void setCount(int count) {
        this.count = count;
    }
}

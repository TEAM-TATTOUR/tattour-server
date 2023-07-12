package org.tattour.server.domain.sticker.provider.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrderSheetStickerInfo {
    private String mainImageUrl;
    private String name;
    private Integer price;
    private Integer discountedPrice;
    private Integer count;

    private GetOrderSheetStickerInfo(String mainImageUrl, String name, Integer price,
            Integer discountedPrice) {
        this.mainImageUrl = mainImageUrl;
        this.name = name;
        this.price = price;
        this.discountedPrice = discountedPrice;
    }

    public static GetOrderSheetStickerInfo of(String mainImageUrl, String name, Integer price, Integer discountedPrice){
        return new GetOrderSheetStickerInfo(mainImageUrl, name, price, discountedPrice);
    }

    public void setCount(int count){
        this.count = count;
    }
}

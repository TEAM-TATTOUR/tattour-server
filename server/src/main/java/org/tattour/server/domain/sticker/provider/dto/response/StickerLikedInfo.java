package org.tattour.server.domain.sticker.provider.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StickerLikedInfo {
    private Integer id;
    private String name;
    private Integer price;
    private Integer discountRate;
    private Integer discountPrice;
    private String mainImageUrl;
}

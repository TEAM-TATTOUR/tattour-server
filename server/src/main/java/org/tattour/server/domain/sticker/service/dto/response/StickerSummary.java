package org.tattour.server.domain.sticker.service.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.sticker.domain.Sticker;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class StickerSummary {

	private Integer id;
	private String name;
	private String imageUrl;
	private Integer price;
	private Integer discountRate;
	private Integer discountPrice;
	private Boolean isCustom;

	public static StickerSummary of(Sticker sticker) {
		Integer discountRate = sticker.getDiscount().getDiscountRate();
		return StickerSummary.builder()
			.id(sticker.getId())
			.name(sticker.getName())
			.imageUrl(sticker.getMainImageUrl())
			.price(sticker.getPrice())
			.discountRate(discountRate)
			.discountPrice(sticker.getPrice()*(100-discountRate)/100)
			.isCustom(sticker.getIsCustom())
			.build();
	}
}

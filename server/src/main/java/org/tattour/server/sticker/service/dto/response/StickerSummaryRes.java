package org.tattour.server.sticker.service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.sticker.domain.Sticker;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class StickerSummaryRes {

	private Integer id;
	private String name;
	private String imageUrl;
	private Integer price;
	private Integer discountRate;
	private Integer discountPrice;
	private Boolean isCustom;

	public static StickerSummaryRes of(Sticker sticker) {
		Integer discountRate = sticker.getDiscount().getDiscountRate();
		return StickerSummaryRes.builder()
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

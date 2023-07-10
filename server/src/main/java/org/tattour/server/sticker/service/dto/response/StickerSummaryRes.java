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
	private Boolean isCustom;

	public static StickerSummaryRes of(Sticker sticker) {
		return StickerSummaryRes.builder()
			.id(sticker.getId())
			.name(sticker.getName())
			.imageUrl(sticker.getMainImageUrl())
			.price(sticker.getPrice())
			.isCustom(sticker.getIsCustom())
			.build();
	}
}

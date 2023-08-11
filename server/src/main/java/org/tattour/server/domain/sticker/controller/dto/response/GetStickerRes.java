package org.tattour.server.domain.sticker.controller.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerRes;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class GetStickerRes {

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

	@Schema(description = "스티커 이미지")
	private List<String> images;


	public static GetStickerRes from(ReadStickerRes readStickerRes) {
		return GetStickerRes.builder()
				.id(readStickerRes.getId())
				.name(readStickerRes.getName())
				.description(readStickerRes.getDescription())
				.price(readStickerRes.getPrice())
				.discountRate(readStickerRes.getDiscountRate())
				.discountPrice(readStickerRes.getDiscountPrice())
				.composition(readStickerRes.getComposition())
				.size(readStickerRes.getSize())
				.isCustom(readStickerRes.getIsCustom())
				.shippingCost(3000)
				.stickerThemes(readStickerRes.getStickerThemes())
				.stickerStyles(readStickerRes.getStickerStyles())
				.images(readStickerRes.getImages())
				.build();
	}
}

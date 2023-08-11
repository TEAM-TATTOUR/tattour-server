package org.tattour.server.domain.sticker.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class PostStickerReq {

	@Schema(description = "스티커 이름", example = "스티커 이름")
	private String name;

	@Schema(description = "스티커 메인 이미지")
	private MultipartFile mainImage;

	@Schema(description = "스티커 이미지들")
	private List<MultipartFile> images;

	@Schema(description = "커스텀 스티커 여부", example = "true")
	private Boolean isCustom;

	@Schema(description = "스티커 가격", example = "1500")
	private Integer price;

	@Schema(description = "스티커 구성", example = "스티커 구성")
	private String composition;

	@Schema(description = "스티커 크기", example = "스티커 크기")
	private String size;

	@Schema(description = "스티커 배송비", example = "3000")
	private Integer shippingFee;

	@Schema(description = "스티커 테마들", example = "[1, 2, 3]")
	private List<Integer> themes;

	@Schema(description = "스티커 스타일들", example = "[1, 2, 3]")
	private List<Integer> styles;

	@Schema(description = "스티커 설명", example = "스티커 설명")
	private String description;

	public static PostStickerReq of(
		String name,
		MultipartFile mainImage,
		List<MultipartFile> images,
		Boolean isCustom,
		Integer price,
		String composition,
		String size,
		Integer shippingFee,
		List<Integer> themes,
		List<Integer> styles,
		String description) {
		return PostStickerReq.builder()
			.name(name)
			.mainImage(mainImage)
			.images(images)
			.isCustom(isCustom)
			.price(price)
			.composition(composition)
			.size(size)
			.shippingFee(shippingFee)
			.themes(themes)
			.styles(styles)
			.description(description)
			.build();
	}
}

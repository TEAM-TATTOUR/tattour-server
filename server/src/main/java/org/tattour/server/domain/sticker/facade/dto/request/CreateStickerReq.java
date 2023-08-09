package org.tattour.server.domain.sticker.facade.dto.request;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CreateStickerReq {

	private String name;
	private MultipartFile mainImage;
	private List<MultipartFile> images;
	private Boolean isCustom;
	private Integer price;
	private String composition;
	private String size;
	private Integer shippingFee;
	private List<Integer> themes;
	private List<Integer> styles;
	private String description;

	public static CreateStickerReq of(
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
		return CreateStickerReq.builder()
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

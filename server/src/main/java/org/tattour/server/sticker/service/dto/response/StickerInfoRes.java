package org.tattour.server.sticker.service.dto.response;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import org.tattour.server.sticker.domain.Sticker;
import org.tattour.server.sticker.domain.StickerImage;

@Builder(access = AccessLevel.PRIVATE)
public class StickerInfoRes {

	private Long id;
	private String name;
	private String description;
	private int price;
	private String composition;
	private int width;
	private int height;
	private boolean isCustom;
	private int shippingCost;
	private List<String> stickerThemes;
	private List<String> stickerStyles;
	private List<String> images;


	public static StickerInfoRes from(Sticker sticker, List<StickerImage> images) {
		List<String> stickerImages = new ArrayList<>();
		List<String> stickerThemes = new ArrayList<>();
		List<String> stickerStyles = new ArrayList<>();
		stickerImages.add(sticker.getMainImageUrl());
		images.stream()
			.map(image -> stickerImages.add(image.getImageUrl()))
			.collect(Collectors.toList());
		sticker.getStickerThemes()
			.stream()
			.map(theme -> stickerThemes.add(theme.getTheme().getName()))
			.collect(Collectors.toList());
		sticker.getStickerStyles()
			.stream()
			.map(style -> stickerStyles.add(style.getStyle().getName()))
			.collect(Collectors.toList());
		return StickerInfoRes.builder()
			.id(sticker.getId())
			.name(sticker.getName())
			.description(sticker.getDescription())
			.price(sticker.getPrice())
			.composition(sticker.getComposition())
			.width(sticker.getWidth())
			.height(sticker.getHeight())
			.isCustom(sticker.isCustom())
			.shippingCost(3000)
			.stickerThemes(stickerThemes)
			.stickerStyles(stickerStyles)
			.images(stickerImages)
			.build();
	}
}

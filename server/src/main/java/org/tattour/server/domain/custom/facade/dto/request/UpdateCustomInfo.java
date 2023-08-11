package org.tattour.server.domain.custom.facade.dto.request;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.custom.domain.CustomProcess;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateCustomInfo {

	private Integer userId;
	private Integer customId;
	private String size;
	private List<MultipartFile> images;
	private MultipartFile handDrawingImage;
	private Boolean isColored;
	private List<Integer> themes;
	private List<Integer> styles;
	private String name;
	private String description;
	private String demand;
	private Integer count;
	private Boolean isPublic;
	private Boolean isCompleted;
	private Integer viewCount;
	private Integer price;
	private CustomProcess customProcess;

	public static UpdateCustomInfo of(
			Integer userId,
			Integer customId,
			String size,
			List<MultipartFile> images,
			MultipartFile handDrawingImage,
			Boolean isColored,
			List<Integer> themes,
			List<Integer> styles,
			String name,
			String description,
			String demand,
			Integer count,
			Boolean isPublic,
			Boolean isCompleted,
			Integer viewCount,
			Integer price,
			CustomProcess customProcess) {
		return UpdateCustomInfo.builder()
				.userId(userId)
				.customId(customId)
				.size(size)
				.images(images)
				.handDrawingImage(handDrawingImage)
				.isColored(isColored)
				.themes(themes)
				.styles(styles)
				.name(name)
				.description(description)
				.demand(demand)
				.count(count)
				.isPublic(isPublic)
				.isCompleted(isCompleted)
				.viewCount(viewCount)
				.price(price)
				.customProcess(customProcess)
				.build();
	}

	public static UpdateCustomInfo of(
			Integer userId,
			Integer customId,
			CustomProcess customProcess) {
		return UpdateCustomInfo.builder()
				.userId(userId)
				.customId(customId)
				.customProcess(customProcess)
				.build();
	}
}

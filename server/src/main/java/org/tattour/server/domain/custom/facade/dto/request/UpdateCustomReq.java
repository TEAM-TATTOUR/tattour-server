package org.tattour.server.domain.custom.facade.dto.request;

import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.domain.Custom.CustomBuilder;
import org.tattour.server.domain.custom.domain.CustomProcess;
import org.tattour.server.domain.custom.domain.CustomSize;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateCustomReq {

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

	public static UpdateCustomReq of(
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
		return UpdateCustomReq.builder()
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

	public static UpdateCustomReq of(
			Integer userId,
			Integer customId,
			CustomProcess customProcess) {
		return UpdateCustomReq.builder()
				.userId(userId)
				.customId(customId)
				.customProcess(customProcess)
				.build();
	}

	public Custom newCustom() {
		Custom.CustomBuilder custom = Custom.builder();
		custom.size(getCustomSize());
		custom.isColored(isColored);
		custom.name(name);
		custom.demand(demand);
		custom.description(description);
		custom.isPublic(isPublic);
		custom.count(count);
		custom.isCompleted(isCompleted);
		custom.process(getCustomProcess());
		custom.viewCount(viewCount);
		custom.price(price);
		return custom.build();
	}

	private CustomSize getCustomSize() {
		if (size == null) {
			return null;
		}
		return CustomSize.getCustomSize(size);
	}

	public CustomProcess getCustomProcess() {
		return isCompleted ? CustomProcess.RECEIVING : null;
	}
}

package org.tattour.server.domain.custom.service.dto.request;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CreateCustomInfo {

	private Boolean haveDesign;
	private Integer pageCount;
	private String size;
	private MultipartFile mainImage;
	private List<MultipartFile> images;
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

	public static CreateCustomInfo from(Boolean haveDesign, Integer pageCount, String size,
		MultipartFile mainImage, List<MultipartFile> images, Boolean isColored,
		List<Integer> themes, List<Integer> styles, String name, String description, String demand,
		Integer count, Boolean isPublic, Boolean isCompleted, Integer viewCount) {
		return CreateCustomInfo.builder()
			.haveDesign(haveDesign)
			.pageCount(pageCount)
			.size(size)
			.mainImage(mainImage)
			.images(images)
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
			.build();
	}
}

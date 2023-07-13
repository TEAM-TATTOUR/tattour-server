package org.tattour.server.domain.custom.service.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.custom.domain.Custom;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CustomInfo {

	private Integer id;
	private Integer userId;
	private Integer stickerId;
	private List<String> themes;
	private List<String> styles;
	private String mainImageUrl;
	private List<String> images;
	private Boolean haveDesign;
	private String size;
	private String name;
	private String description;
	private String demand;
	private Integer count;
	private Boolean isColored;
	private Boolean isPublic;
	private Boolean isCompleted;
	private String process;
	private Integer viewCount;

	public static CustomInfo of(Custom custom) {
		List<String> themes = custom.getCustomThemes().stream()
			.map(customTheme -> customTheme.getTheme().getName())
			.collect(Collectors.toList());
		List<String> styles = custom.getCustomStyles().stream()
			.map(customStyle -> customStyle.getStyle().getName())
			.collect(Collectors.toList());
		List<String> images = custom.getImages().stream()
			.map(customImage -> customImage.getImageUrl())
			.collect(Collectors.toList());
		return CustomInfo.builder()
			.id(custom.getId())
			.userId(custom.getUser().getId())
			.themes(themes)
			.styles(styles)
			.mainImageUrl(custom.getMainImageUrl())
			.images(images)
			.haveDesign(custom.getHaveDesign())
			.size(custom.getSize().getSize())
			.name(custom.getName())
			.description(custom.getDescription())
			.demand(custom.getDemand())
			.count(custom.getCount())
			.isColored(custom.getIsColored())
			.isPublic(custom.getIsPublic())
			.isCompleted(custom.getIsCompleted())
			.process(custom.getProcess().getValue())
			.viewCount(custom.getViewCount())
			.build();
	}
}

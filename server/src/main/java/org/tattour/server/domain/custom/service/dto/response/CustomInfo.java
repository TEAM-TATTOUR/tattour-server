package org.tattour.server.domain.custom.service.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class CustomInfo {

	private Boolean haveDesign;
	private Integer pageCount;
	private Integer size;
	private String mainImageUrl;
	private List<String> images;
	private Boolean isColored;
	private List<String> themes;
	private List<String> styles;
	private String name;
	private String description;
	private String demand;
	private Integer count;
	private Boolean isPublic;
	private Boolean isCompleted;
	private Integer viewCount;
}

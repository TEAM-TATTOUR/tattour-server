package org.tattour.server.domain.custom.controller.dto.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.custom.service.dto.request.CreateCustomInfo;

@Getter
@Setter
@NoArgsConstructor
public class ApplyCustomReq {

	private Boolean haveDesign;
	private Integer pageCount;
	private String size;
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

	public CreateCustomInfo newCustomInfo(MultipartFile mainImage, List<MultipartFile> images) {
		return CreateCustomInfo.from(haveDesign, pageCount, size, mainImage, images, isColored,
			themes, styles, name, description, demand, count, isPublic, isCompleted, viewCount);
	}
}

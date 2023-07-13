package org.tattour.server.domain.custom.controller.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.custom.service.dto.request.UpdateCustomInfo;

@Getter
@NoArgsConstructor
public class UpdateCustomReq {

	private Integer customId;
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

	public UpdateCustomInfo newUpdateCustomInfo(MultipartFile mainImage, List<MultipartFile> images) {
		return UpdateCustomInfo.from(customId, size, mainImage, images, isColored,
			themes, styles, name, description, demand, count, isPublic, isCompleted, viewCount);
	}

}
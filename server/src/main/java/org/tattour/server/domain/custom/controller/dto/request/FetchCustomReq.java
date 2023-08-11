package org.tattour.server.domain.custom.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.custom.domain.CustomProcess;
import org.tattour.server.domain.custom.facade.dto.request.UpdateCustomInfo;

@Getter
@NoArgsConstructor
public class FetchCustomReq {

	@NotNull
	@Schema(description = "커스텀 id")
	private Integer customId;

	@Schema(description = "커스텀 사이즈", example = "quarter, half, regular, double")
	private String size;

	@Schema(description = "커스텀 색상 여부")
	private Boolean isColored;

	@Schema(description = "커스텀 테마")
	private List<Integer> themes;

	@Schema(description = "커스텀 스타일")
	private List<Integer> styles;

	@Schema(description = "커스텀 이름")
	private String name;

	@Schema(description = "커스텀 설명")
	private String description;

	@Schema(description = "커스텀 요구사항")
	private String demand;

	@Schema(description = "주문 스티커 수량")
	private Integer count;

	@Schema(description = "커스텀 공개 여부")
	private Boolean isPublic;

	@Schema(description = "커스텀 완성 여부")
	private Boolean isCompleted;

	@Schema(description = "계산된 커스텀 가격")
	private Integer price;

	@Schema(description = "작성중인 커스텀 뷰 번호")
	private Integer viewCount;

	public UpdateCustomInfo newUpdateCustomInfo(
		Integer userId,
		List<MultipartFile> images,
		MultipartFile handDrawingImage,
		CustomProcess customProcess) {
		return UpdateCustomInfo.of(
			userId,
			customId,
			size,
			images,
			handDrawingImage,
			isColored,
			themes,
			styles,
			name,
			description,
			demand,
			count,
			isPublic,
			isCompleted,
			viewCount,
			price,
			customProcess);
	}
}
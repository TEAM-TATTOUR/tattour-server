package org.tattour.server.domain.custom.facade.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "커스텀 신청내역 요약 정보 리스트")
@Getter
@Setter
public class CreateCustomSummaryRes {

	@Schema(description = "커스텀 신청 id")
	private Integer id;

	@Schema(description = "메인 배너 이미지 url")
	private String mainImageUrl;

	@Schema(description = "커스텀 신청 이름")
	private String name;

	@Schema(description = "설명")
	private String description;

	@Schema(description = "생성일자")
	private String createdAt;
}

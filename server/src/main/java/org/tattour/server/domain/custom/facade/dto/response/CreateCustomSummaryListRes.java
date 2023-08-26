package org.tattour.server.domain.custom.facade.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "커스텀 신청내역 요약 정보 리스트")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateCustomSummaryListRes {

	List<CreateCustomSummaryRes> createCustomSummaryResList;

	public static CreateCustomSummaryListRes from(
			List<CreateCustomSummaryRes> createCustomSummaryResList) {
		return new CreateCustomSummaryListRes(createCustomSummaryResList);
	}
}

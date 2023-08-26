package org.tattour.server.domain.style.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.style.facade.dto.response.ReadStyleSummaryListRes;
import org.tattour.server.domain.style.facade.dto.response.ReadStyleSummaryRes;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetStyleSummaryListRes {

	@Schema(description = "스타일 요약 정보 리스트")
	List<GetStyleSummaryRes> styleSummaries;

	public static GetStyleSummaryListRes from(ReadStyleSummaryListRes readStyleSummaryListRes) {
		return new GetStyleSummaryListRes(
				readStyleSummaryListRes
						.getStyleSummaries()
						.stream()
						.map(GetStyleSummaryRes::from)
						.collect(Collectors.toList())
		);
	}

}

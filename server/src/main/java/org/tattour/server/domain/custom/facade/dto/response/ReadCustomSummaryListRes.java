package org.tattour.server.domain.custom.facade.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.custom.model.Custom;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadCustomSummaryListRes {

	private List<ReadCustomSummaryRes> customs;

	public static ReadCustomSummaryListRes from(List<Custom> customs) {
		List<ReadCustomSummaryRes> customSummaries = customs
				.stream()
				.map(ReadCustomSummaryRes::from)
				.collect(Collectors.toList());
		return new ReadCustomSummaryListRes(customSummaries);
	}
}

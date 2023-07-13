package org.tattour.server.domain.custom.service.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.custom.domain.Custom;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomSummaryList {

	private List<CustomSummary> customs;

	public static CustomSummaryList of(List<Custom> customs) {
		List<CustomSummary> customSummaries = customs.stream()
			.map(CustomSummary::of)
			.collect(Collectors.toList());
		return new CustomSummaryList(customSummaries);
	}
}

package org.tattour.server.domain.theme.service.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.theme.domain.Theme;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ThemeSummaryList {

	List<ThemeSummary> themeSummaries;

	public static ThemeSummaryList of(List<Theme> themes) {
		List<ThemeSummary> themeSummaries = themes.stream()
			.map(ThemeSummary::of)
			.collect(Collectors.toList());
		return new ThemeSummaryList(themeSummaries);
	}
}

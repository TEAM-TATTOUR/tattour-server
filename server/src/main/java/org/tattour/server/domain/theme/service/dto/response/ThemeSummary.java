package org.tattour.server.domain.theme.service.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.theme.domain.Theme;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ThemeSummary {

	private Integer id;
	private String name;

	public static ThemeSummary of(Theme theme) {
		return ThemeSummary.builder()
			.id(theme.getId())
			.name(theme.getName())
			.build();
	}
}
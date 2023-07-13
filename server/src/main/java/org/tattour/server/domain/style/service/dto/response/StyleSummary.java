package org.tattour.server.domain.style.service.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.style.domain.Style;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class StyleSummary {

	private Integer id;
	private String name;

	public static StyleSummary of(Style style) {
		return StyleSummary.builder()
			.id(style.getId())
			.name(style.getName())
			.build();
	}
}

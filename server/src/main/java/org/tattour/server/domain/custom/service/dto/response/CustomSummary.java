package org.tattour.server.domain.custom.service.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.custom.domain.Custom;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CustomSummary {

	private Integer id;
	private String name;
	private String imageUrl;

	public static CustomSummary of(Custom custom) {
		return CustomSummary.builder()
			.id(custom.getId())
			.name(custom.getName())
			.imageUrl(custom.getMainImageUrl())
			.build();

	}
}

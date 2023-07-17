package org.tattour.server.domain.custom.controller.dto.response;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplyCustomRes {

	@NotNull
	private Integer customId;

	public static ApplyCustomRes of(Integer customId) {
		return new ApplyCustomRes(customId);
	}
}

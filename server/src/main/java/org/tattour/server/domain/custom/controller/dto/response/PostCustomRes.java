package org.tattour.server.domain.custom.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCustomRes {

	@NotNull
	@Schema(description = "생성된 customId")
	private Integer customId;

	public static PostCustomRes from(Integer customId) {
		return new PostCustomRes(customId);
	}
}

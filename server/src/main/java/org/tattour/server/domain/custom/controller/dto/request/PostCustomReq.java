package org.tattour.server.domain.custom.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PostCustomReq {

	@NotNull(message = "커스텀 사이즈는 필수 값입니다.")
	@Schema(description = "그려둔 도안이 있는지")
	private Boolean haveDesign;
}

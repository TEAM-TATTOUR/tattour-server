package org.tattour.server.domain.admin.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateStickerRes {

	@Schema(description = "생성된 sticker Id")
	private Integer stickerId;
}

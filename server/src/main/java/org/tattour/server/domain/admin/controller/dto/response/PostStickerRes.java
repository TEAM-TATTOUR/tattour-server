package org.tattour.server.domain.admin.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PostStickerRes {

	@Schema(description = "생성된 sticker Id")
	private Integer stickerId;

	public static PostStickerRes of(Integer stickerId) {
		return new PostStickerRes(stickerId);
	}
}

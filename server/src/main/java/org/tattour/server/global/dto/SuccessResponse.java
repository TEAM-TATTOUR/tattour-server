package org.tattour.server.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "성공 Response")
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {

	@Schema(description = "HTTP 상태코드")
	private final int code;

	@Schema(description = "메시지")
	private final String message;

	@Schema(description = "데이터")
	private T data;
}

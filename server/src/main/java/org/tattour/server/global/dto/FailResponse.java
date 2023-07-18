package org.tattour.server.global.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "실패 Response")
@Getter
@AllArgsConstructor
public class FailResponse {

	@Schema(description = "HTTP 상태코드")
	private int code;

	@Schema(description = "에러 메시지")
	private String message;
}

package org.tattour.server.domain.style.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.style.service.StyleService;
import org.tattour.server.domain.style.service.dto.response.StyleInfoList;
import org.tattour.server.domain.style.service.dto.response.StyleSummaryList;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/style")
@Tag(name = "Style", description = "Style API Document")
public class StyleController {

	private final StyleService styleService;

	@GetMapping
	@Operation(summary = "스타일 리스트 조회")
	public ResponseEntity<?> getStyleList() {
		StyleInfoList response = styleService.getAllStyle();
		return BaseResponse.success(SuccessType.READ_ALL_STYLE_SUCCESS, response);
	}

	@GetMapping("/summary")
	@Operation(summary = "스타일 요약 정보 리스트 조회")
	public ResponseEntity<?> getStyleSummaryList() {
		StyleSummaryList response = styleService.getAllStyleSummary();
		return BaseResponse.success(SuccessType.READ_ALL_STYLE_SUMMARY_SUCCESS, response);
	}
}

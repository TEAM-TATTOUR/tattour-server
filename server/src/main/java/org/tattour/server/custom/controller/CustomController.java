package org.tattour.server.custom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.custom.service.CustomService;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/custom")
public class CustomController {

	private final CustomService customService;

	@PostMapping
	public ResponseEntity<?> createCustom() {
		return ApiResponse.success(SuccessType.LOGIN_SUCCESS);
	}


	/*
	@GetMapping("/custom/hot")
	@Operation(summary = "인기 커스텀 스티커 조회", description = "주문이 가장 많은 커스텀 스티커 조회")
	public ResponseEntity<?> getHotCustomStickerList() {
		StickerSummaryListRes response = stickerService.getHotCustomStickerList();
		return ApiResponse.success(SuccessType.READ_HOT_CUSTOM_STICKER_SUCCESS, response);
	}
	 */
}

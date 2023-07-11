package org.tattour.server.domain.custom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.custom.controller.dto.request.CreateCustomReq;
import org.tattour.server.domain.custom.service.CustomService;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/custom")
@Tag(name = "Custom", description = "Custom API Document")
public class CustomController {

	private final CustomService customService;

	@PostMapping("/apply")
	@Operation(summary = "커스텀 도안 신청", description = "커스텀 도안 신청 api")
	public ResponseEntity<?> createCustom(@RequestBody CreateCustomReq request) {
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

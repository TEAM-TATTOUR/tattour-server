package org.tattour.server.sticker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.golbal.dto.ApiResponse;
import org.tattour.server.golbal.dto.SuccessType;
import org.tattour.server.sticker.service.StickerService;
import org.tattour.server.sticker.service.dto.response.StickerInfoRes;
import org.tattour.server.sticker.service.dto.response.StickerSummaryListRes;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stickers")
@Tag(name = "Sticker", description = "Sticker API Document")
public class StickerController {

	private final StickerService stickerService;

	@GetMapping
	@Operation(summary = "스티커 전체 조회")
	public ResponseEntity<?> getAllStickerList() {
		StickerSummaryListRes response = stickerService.getAllStickerList();
		return ApiResponse.success(SuccessType.READ_ALL_STICKER_SUCCESS, response);
	}

	@GetMapping("/asd")
	@Operation(summary = "인기 커스텀 스티커 조회", description = "주문이 가장 많은 커스텀 스티커 조회")
	public ResponseEntity<?> getHotCustomStickerList() {
		StickerSummaryListRes response = stickerService.getHotCustomStickerList();
		return ApiResponse.success(SuccessType.READ_HOT_CUSTOM_STICKER_SUCCESS, response);
	}

	@GetMapping("/oo")
	@Operation(summary = "커스텀 스티커 상세 정보 조회", description = "스티커 아이디 받음")
	public ResponseEntity<?> getOneStickerInfo(@RequestParam(name = "stickerId") Long stickerId) {
		StickerInfoRes response = stickerService.getOneStickerInfo(stickerId);
		return ApiResponse.success(SuccessType.READ_STICKER_INFO_SUCCESS, response);
	}
}

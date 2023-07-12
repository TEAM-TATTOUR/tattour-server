package org.tattour.server.domain.custom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.custom.controller.dto.request.ApplyCustomReq;
import org.tattour.server.domain.custom.controller.dto.response.ApplyCustomRes;
import org.tattour.server.domain.custom.service.CustomService;
import org.tattour.server.domain.custom.service.dto.response.CustomInfo;
import org.tattour.server.global.config.resolver.UserId;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/custom")
@Tag(name = "Custom", description = "Custom API Document")
public class CustomController {

	private final CustomService customService;

	@PostMapping(value = "/apply", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "커스텀 도안 신청", description = "haveDesign은 notNull이어야함!"
		+ " data 는 application/json 타입으로, mainImage 는 file. image 는 file 리스트로 보내기!")
	public ResponseEntity<?> createCustom(
//		@UserId Integer userId,
		@RequestPart(value = "data") ApplyCustomReq request,
		@RequestPart(value = "mainImage", required = false) MultipartFile mainImage,
		@RequestPart(value = "images", required = false) List<MultipartFile> images
		) {
		CustomInfo customInfo = customService.createCustom(request.newCustomInfo(mainImage, images), 1);
		ApplyCustomRes response = ApplyCustomRes.of(customInfo);
		return ApiResponse.success(SuccessType.CREATE_CUSTOM_SUCCESS, response);
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

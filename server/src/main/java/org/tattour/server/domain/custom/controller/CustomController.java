package org.tattour.server.domain.custom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.custom.controller.dto.request.ApplyCustomReq;
import org.tattour.server.domain.custom.controller.dto.request.UpdateCustomReq;
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

	@PostMapping(value = "/apply")
	@Operation(summary = "커스텀 도안 신청", description = "포인트 결제하면 haveDesign 만 넘겨주기")
	public ResponseEntity<?> createCustom(
//		@UserId Integer userId,
		@RequestBody ApplyCustomReq request
	) {
		ApplyCustomRes response = ApplyCustomRes.of(
			(customService.createCustom(request.getHaveDesign(), 1)));
		return ApiResponse.success(SuccessType.CREATE_CUSTOM_SUCCESS, response);
	}

	@PatchMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "커스텀 도안 수정", description = " data 는 application/json 타입으로 보내기"
		+ " mainImage 는 file. image 는 file 리스트로 보내기!")
	public ResponseEntity<?> updateCustom(
//		@UserId Integer userId,
		@RequestPart(value = "data") UpdateCustomReq request,
		@RequestPart(value = "mainImage", required = false) MultipartFile mainImage,
		@RequestPart(value = "images", required = false) List<MultipartFile> images
	) {
		CustomInfo response = customService.updateCustom(request.newUpdateCustomInfo(1, mainImage, images));
		return ApiResponse.success(SuccessType.UPDATE_CUSTOM_SUCCESS, response);
	}
}

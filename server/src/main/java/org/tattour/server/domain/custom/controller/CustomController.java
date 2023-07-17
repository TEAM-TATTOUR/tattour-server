package org.tattour.server.domain.custom.controller;

	import io.swagger.v3.oas.annotations.Operation;
	import io.swagger.v3.oas.annotations.Parameter;
	import io.swagger.v3.oas.annotations.media.Content;
	import io.swagger.v3.oas.annotations.media.Schema;
	import io.swagger.v3.oas.annotations.responses.ApiResponse;
	import io.swagger.v3.oas.annotations.responses.ApiResponses;
	import io.swagger.v3.oas.annotations.security.SecurityRequirement;
	import io.swagger.v3.oas.annotations.tags.Tag;
	import java.util.List;
	import javax.validation.Valid;
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
	import org.tattour.server.global.dto.BaseResponse;
	import org.tattour.server.global.dto.FailResponse;
	import org.tattour.server.global.dto.SuccessType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/custom")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Custom", description = "Custom API Document")
public class CustomController {

	private final CustomService customService;

	@PostMapping(value = "/apply")
	@Operation(summary = "커스텀 도안 신청")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "success",
			content = @Content(schema = @Schema(implementation = ApplyCustomRes.class))),
		@ApiResponse(responseCode = "400, 500", description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> createCustom(
		@Parameter(hidden = true) @UserId Integer userId,
		@Parameter(name = "customInfo",  description = "haveDesign 만 넘겨주기")
		@RequestBody @Valid ApplyCustomReq request
	) {

		ApplyCustomRes response = ApplyCustomRes.of(
			(customService.createCustom(request.getHaveDesign(), userId)));
		return BaseResponse.success(SuccessType.CREATE_CUSTOM_SUCCESS, response);
	}

	@PatchMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "커스텀 도안 수정", description = "customInfo ContentType: application/json"
		+ "\ncustomId 를 제외한 모든 컬럼 null 값 가능"
		+ "\n테마, 스타일 타입은  Integer")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "success",
			content = @Content(schema = @Schema(implementation = CustomInfo.class))),
		@ApiResponse(responseCode = "400, 500", description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> updateCustom(
		@Parameter(hidden = true) @UserId Integer userId,
		@RequestPart @Valid UpdateCustomReq customInfo,
		@RequestPart MultipartFile customMainImage,
		@RequestPart(required = false) List<MultipartFile> customImages
	) {
		CustomInfo response = customService.updateCustom(
			customInfo.newUpdateCustomInfo(userId, customMainImage, customImages));
		return BaseResponse.success(SuccessType.UPDATE_CUSTOM_SUCCESS, response);
	}
}
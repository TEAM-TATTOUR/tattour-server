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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.custom.controller.dto.request.PostCustomReq;
import org.tattour.server.domain.custom.controller.dto.request.PatchCustomReq;
import org.tattour.server.domain.custom.controller.dto.response.PatchCustomRes;
import org.tattour.server.domain.custom.controller.dto.response.PostCustomRes;
import org.tattour.server.domain.custom.facade.CustomFacade;
import org.tattour.server.global.config.resolver.UserId;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessType;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/custom")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Custom", description = "Custom API Document")
public class CustomController {

	private final CustomFacade customFacade;

	@PostMapping(value = "/apply")
	@Operation(summary = "커스텀 도안 신청")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "success",
			content = @Content(schema = @Schema(implementation = PostCustomRes.class))),
		@ApiResponse(responseCode = "400, 500", description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> postCustom(
		@Parameter(hidden = true) @UserId Integer userId,
		@RequestBody @Valid PostCustomReq request
	) {
		PostCustomRes response = PostCustomRes.from(
			(customFacade.createCustom(request.getHaveDesign(), userId)));
		return BaseResponse.success(SuccessType.CREATE_CUSTOM_SUCCESS, response);
	}

	@PatchMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "커스텀 도안 수정", description = "customInfo ContentType: application/json"
		+ " / customId 를 제외한 모든 컬럼 null 값 가능"
		+ " / size :  <receiving, receiptComplete, receiptFailed, shipping, shipped> "
		+ " / 테마, 스타일 타입은  Integer")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "success",
			content = @Content(schema = @Schema(implementation = PatchCustomRes.class))),
		@ApiResponse(responseCode = "400, 500", description = "error",
			content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> patchCustom(
		@Parameter(hidden = true) @UserId Integer userId,
		@RequestPart @Valid PatchCustomReq customInfo,
		@RequestPart(required = false) MultipartFile handDrawingImage,
		@RequestPart(required = false)  List<MultipartFile> customImages
	) {
		PatchCustomRes response =
			PatchCustomRes.from(
				customFacade.updateCustom(
					customInfo.newUpdateCustomReq(
						userId,
						customImages,
						handDrawingImage)));
		return BaseResponse.success(SuccessType.UPDATE_CUSTOM_SUCCESS, response);
	}
}
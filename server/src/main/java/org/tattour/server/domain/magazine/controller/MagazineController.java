package org.tattour.server.domain.magazine.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.magazine.controller.dto.response.GetMagazineListRes;
import org.tattour.server.domain.magazine.controller.dto.response.GetMagazineUrlRes;
import org.tattour.server.domain.magazine.facade.MagazineFacade;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessType;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/magazines")
@SecurityRequirement(name = "JWT Auth")
@Tag(name = "Magazine", description = "Magazine API Document")
public class MagazineController {

	private final MagazineFacade magazineFacade;

	@GetMapping
	@Operation(summary = "매거진 전체 조회")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "success",
					content = @Content(schema = @Schema(implementation = GetMagazineListRes.class))),
			@ApiResponse(
					responseCode = "400, 500",
					description = "error",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getMagazineList() {
		GetMagazineListRes response =
				GetMagazineListRes.from(
						magazineFacade.readMagazineList());
		return BaseResponse.success(
				SuccessType.READ_ALL_MAGAZINE_SUCCESS,
				response);
	}

	@GetMapping("/{magazineId}")
	@Operation(summary = "id 로 매거진 한 개 url 조회")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "success",
					content = @Content(schema = @Schema(implementation = GetMagazineUrlRes.class))),
			@ApiResponse(
					responseCode = "400, 500",
					description = "error",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getOneMagazineUrl(@PathVariable Integer magazineId) {
		GetMagazineUrlRes response =
				GetMagazineUrlRes.from(
						magazineFacade.readMagazineUrlById(magazineId));
		return BaseResponse.success(
				SuccessType.READ_ONE_MAGAZINE_SUCCESS,
				response);
	}
}

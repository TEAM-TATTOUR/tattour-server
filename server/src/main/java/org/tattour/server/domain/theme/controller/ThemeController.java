package org.tattour.server.domain.theme.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.theme.controller.dto.response.GetThemeListRes;
import org.tattour.server.domain.theme.controller.dto.response.GetThemeSummaryListRes;
import org.tattour.server.domain.theme.facade.ThemeFacade;
import org.tattour.server.domain.theme.facade.dto.response.ReadThemeListRes;
import org.tattour.server.domain.theme.facade.dto.response.ReadThemeSummaryListRes;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/theme")
@Tag(name = "Theme", description = "Theme API Document")
public class ThemeController {

	private final ThemeFacade themeFacade;

	@GetMapping
	@Operation(summary = "테마 리스트 조회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "success",
					content = @Content(schema = @Schema(implementation = GetThemeListRes.class))),
			@ApiResponse(responseCode = "400, 500",
					description = "error",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getThemeList() {
		GetThemeListRes response =
				GetThemeListRes.from(
						themeFacade.readAllTheme());
		return BaseResponse.success(
				SuccessType.READ_ALL_THEME_SUCCESS,
				response);
	}

	@GetMapping("/summary")
	@Operation(summary = "테마 요약 정보 리스트 조회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",
					description = "success",
					content = @Content(schema = @Schema(implementation = GetThemeSummaryListRes.class))),
			@ApiResponse(responseCode = "400, 500",
					description = "error",
					content = @Content(schema = @Schema(implementation = FailResponse.class)))
	})
	public ResponseEntity<?> getThemeSummaryList() {
		GetThemeSummaryListRes response =
				GetThemeSummaryListRes.from(
						themeFacade.readAllThemeSummary());
		return BaseResponse.success(
				SuccessType.READ_ALL_THEME_SUMMARY_SUCCESS,
				response);
	}
}

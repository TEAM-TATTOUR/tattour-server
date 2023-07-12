package org.tattour.server.domain.theme.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.theme.service.ThemeService;
import org.tattour.server.domain.theme.service.dto.response.ThemeInfoList;
import org.tattour.server.domain.theme.service.dto.response.ThemeSummaryList;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/theme")
@Tag(name = "Theme", description = "Theme API Document")
public class ThemeController {

	private final ThemeService themeService;

	@GetMapping
	@Operation(summary = "테마 리스트 조회")
	public ResponseEntity<?> getThemeList() {
		ThemeInfoList response = themeService.getAllTheme();
		return ApiResponse.success(SuccessType.READ_ALL_THEME_SUCCESS, response);
	}

	@GetMapping("/summary")
	@Operation(summary = "테마 요약 정보 리스트 조회")
	public ResponseEntity<?> getThemeSummaryList() {
		ThemeSummaryList response = themeService.getAllThemeSummary();
		return ApiResponse.success(SuccessType.READ_ALL_THEME_SUMMARY_SUCCESS, response);
	}
}

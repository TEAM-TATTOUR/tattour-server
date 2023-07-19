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
import org.tattour.server.domain.style.service.dto.response.StyleSummaryList;
import org.tattour.server.domain.theme.service.ThemeService;
import org.tattour.server.domain.theme.service.dto.response.ThemeInfoList;
import org.tattour.server.domain.theme.service.dto.response.ThemeSummaryList;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/theme")
@Tag(name = "Theme", description = "Theme API Document")
public class ThemeController {

    private final ThemeService themeService;

    @GetMapping
    @Operation(summary = "테마 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = ThemeInfoList.class))),
            @ApiResponse(responseCode = "400, 500", description = "error",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    public ResponseEntity<?> getThemeList() {
        ThemeInfoList response = themeService.getAllTheme();
        return BaseResponse.success(SuccessType.READ_ALL_THEME_SUCCESS, response);
    }

    @GetMapping("/summary")
    @Operation(summary = "테마 요약 정보 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = ThemeSummaryList.class))),
            @ApiResponse(responseCode = "400, 500", description = "error",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    public ResponseEntity<?> getThemeSummaryList() {
        ThemeSummaryList response = themeService.getAllThemeSummary();
        return BaseResponse.success(SuccessType.READ_ALL_THEME_SUMMARY_SUCCESS, response);
    }
}

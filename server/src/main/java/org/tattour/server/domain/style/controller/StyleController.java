package org.tattour.server.domain.style.controller;

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
import org.tattour.server.domain.style.service.StyleService;
import org.tattour.server.domain.style.service.dto.response.StyleInfoList;
import org.tattour.server.domain.style.service.dto.response.StyleSummaryList;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessType;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/style")
@Tag(name = "Style", description = "Style API Document")
public class StyleController {

    private final StyleService styleService;

    @GetMapping
    @Operation(summary = "스타일 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = StyleInfoList.class))),
            @ApiResponse(responseCode = "400, 500", description = "error",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    public ResponseEntity<?> getStyleList() {
        StyleInfoList response = styleService.getAllStyle();
        return BaseResponse.success(SuccessType.READ_ALL_STYLE_SUCCESS, response);
    }

    @GetMapping("/summary")
    @Operation(summary = "스타일 요약 정보 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = StyleSummaryList.class))),
            @ApiResponse(responseCode = "400, 500", description = "error",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    public ResponseEntity<?> getStyleSummaryList() {
        StyleSummaryList response = styleService.getAllStyleSummary();
        return BaseResponse.success(SuccessType.READ_ALL_STYLE_SUMMARY_SUCCESS, response);
    }
}

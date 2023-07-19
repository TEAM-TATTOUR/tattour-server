package org.tattour.server.domain.sticker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.global.dto.BaseResponse;
import org.tattour.server.global.dto.FailResponse;
import org.tattour.server.global.dto.SuccessType;
import org.tattour.server.domain.sticker.service.StickerService;
import org.tattour.server.domain.sticker.service.dto.response.StickerInfo;
import org.tattour.server.domain.sticker.service.dto.response.StickerSummaryList;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT Auth")
@RequestMapping("/api/v1/stickers")
@Tag(name = "Sticker", description = "Sticker API Document")
public class StickerController {

    private final StickerService stickerService;

    @GetMapping("/custom/hot")
    @Operation(summary = "인기 커스텀 스티커 조회", description = "주문이 가장 많은 커스텀 스티커 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = StickerSummaryList.class))),
            @ApiResponse(responseCode = "400, 500", description = "error",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    public ResponseEntity<?> getHotCustomStickerList() {
        StickerSummaryList response = stickerService.getHotCustomStickerList();
        return BaseResponse.success(SuccessType.READ_HOT_CUSTOM_STICKER_SUCCESS, response);
    }

    @GetMapping("/{stickerId}")
    @Operation(summary = "커스텀 스티커 상세 정보 조회", description = "스티커 아이디 받음")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = StickerInfo.class))),
            @ApiResponse(responseCode = "400, 500", description = "error",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    public ResponseEntity<?> getOneStickerInfo(@PathVariable Integer stickerId) {
        StickerInfo response = stickerService.getOneStickerInfo(stickerId);
        return BaseResponse.success(SuccessType.READ_STICKER_INFO_SUCCESS, response);
    }

    @GetMapping("/{stickerId}/related")
    @Operation(summary = "비슷한 스티커 조회", description = "스티커 아이디 받음")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = StickerSummaryList.class))),
            @ApiResponse(responseCode = "400, 500", description = "error",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    public ResponseEntity<?> getSimilarStickerList(@PathVariable Integer stickerId) {
        StickerSummaryList response = stickerService.getSimilarStickerList(stickerId);
        return BaseResponse.success(SuccessType.READ_SIMILAR_STICKER_SUCCESS, response);
    }

    @GetMapping
    @Operation(summary = "스티커 필터링해서 조회", description = " / 테마, 스타일은 존재하는 이름으로")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = StickerSummaryList.class))),
            @ApiResponse(responseCode = "400, 500", description = "error",
                    content = @Content(schema = @Schema(implementation = FailResponse.class)))
    })
    public ResponseEntity<?> getFilterStickerList(
            @Parameter(description = "<popularity, price_low, price_high> 기본값 popularity")
            @RequestParam(name = "sort", defaultValue = "popularity") String sort,
            @Parameter(description = "null값 허용, 테마 이름") @RequestParam(name = "theme", defaultValue = "") String theme,
            @Parameter(description = "null값 허용, 스타일 이름") @RequestParam(name = "style", defaultValue = "") String style
    ) {
        StickerSummaryList response = stickerService.getFilterStickerList(sort, theme, style);
        return BaseResponse.success(SuccessType.READ_FILTER_ALL_STICKER_SUCCESS, response);
    }
}

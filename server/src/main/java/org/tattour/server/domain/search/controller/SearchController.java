package org.tattour.server.domain.search.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.global.dto.JsonResponse;
import org.tattour.server.global.dto.SuccessType;
import org.tattour.server.domain.search.service.SearchService;
import org.tattour.server.domain.sticker.service.dto.response.StickerSummaryList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/search")
@Tag(name = "Search", description = "Search API Document")
public class SearchController {

	private final SearchService searchService;

	@GetMapping("/stickers")
	@Operation(summary = "스티커 검색하기 ", description = "스티커 이름, 테마 이름, 스타일 이름 관련 스티커 리스트 조회")
	public ResponseEntity<?> getSearchStickerList(@RequestParam(name = "word") String word) {
		StickerSummaryList response = searchService.searchSticker(word);
		return JsonResponse.success(SuccessType.READ_SEARCH_ALL_STICKER_SUCCESS, response);
	}
}

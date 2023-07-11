package org.tattour.server.domain.search.service;

import org.tattour.server.domain.sticker.service.dto.response.StickerSummaryListRes;

public interface SearchService {

	StickerSummaryListRes searchSticker(String word);
}

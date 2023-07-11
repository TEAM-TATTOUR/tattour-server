package org.tattour.server.search.service;

import org.tattour.server.sticker.service.dto.response.StickerSummaryListRes;

public interface SearchService {

	StickerSummaryListRes searchSticker(String word);
}

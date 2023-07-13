package org.tattour.server.domain.search.service;

import org.tattour.server.domain.sticker.service.dto.response.StickerSummaryList;

public interface SearchService {

	StickerSummaryList searchSticker(String word);
}

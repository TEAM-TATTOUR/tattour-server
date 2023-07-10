package org.tattour.server.sticker.service;

import org.tattour.server.sticker.service.dto.response.StickerInfoRes;
import org.tattour.server.sticker.service.dto.response.StickerSummaryListRes;

public interface StickerService {
	Sticker getStickerByStickerId(Integer stickerId);

	StickerSummaryListRes getAllStickerList();

	StickerSummaryListRes getHotCustomStickerList();

	StickerInfoRes getOneStickerInfo(Integer stickerId);

	StickerSummaryListRes getSimilarStickerList(Integer stickerId);

	StickerSummaryListRes getSearchStickerList(String word);

	StickerSummaryListRes getFilterStickerList(String order, String theme, String style);
}

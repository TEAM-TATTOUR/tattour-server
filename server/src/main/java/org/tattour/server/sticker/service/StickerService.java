package org.tattour.server.sticker.service;

import java.util.List;
import org.tattour.server.sticker.domain.Sticker;
import org.tattour.server.sticker.service.dto.response.StickerInfoRes;
import org.tattour.server.sticker.service.dto.response.StickerSummaryListRes;

public interface StickerService {

	StickerSummaryListRes getAllStickerList();

	StickerSummaryListRes getHotCustomStickerList();

	StickerSummaryListRes getSimilarStickerList(int stickerId);

	StickerSummaryListRes getSearchStickerList(String word);

	StickerSummaryListRes getFilterStickerList(String order, String theme, String style);

	StickerInfoRes getOneStickerInfo(Long stickerId);
}

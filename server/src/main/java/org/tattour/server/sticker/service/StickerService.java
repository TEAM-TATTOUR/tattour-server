package org.tattour.server.sticker.service;

import java.util.List;
import org.tattour.server.sticker.domain.Sticker;
import org.tattour.server.sticker.service.dto.response.StickerInfoRes;
import org.tattour.server.sticker.service.dto.response.StickerSummaryListRes;
import org.tattour.server.style.domain.Style;
import org.tattour.server.theme.domain.Theme;

public interface StickerService {

	Sticker getStickerByStickerId(Integer stickerId);

	StickerSummaryListRes getAllStickerList();

	StickerSummaryListRes getHotCustomStickerList();

	StickerInfoRes getOneStickerInfo(Integer stickerId);

	StickerSummaryListRes getSimilarStickerList(Integer stickerId);

	StickerSummaryListRes getSearchStickerList(String word);

	StickerSummaryListRes getFilterStickerList(String sort, String theme, String style);

	void addStickerListByThemeList(List<Sticker> stickers, List<Theme> themes);

	void addStickerListByStyleList(List<Sticker> stickers, List<Style> styles);

	void addStickerListByTheme(List<Sticker> stickers, Theme theme);

	void addStickerListByStyle(List<Sticker> stickers, Style style);
}

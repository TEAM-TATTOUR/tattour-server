package org.tattour.server.domain.sticker.service;

import java.util.List;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.service.dto.request.CreateStickerInfo;
import org.tattour.server.domain.sticker.service.dto.response.StickerInfo;
import org.tattour.server.domain.sticker.service.dto.response.StickerSummaryList;
import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.theme.domain.Theme;

public interface StickerService {

	Sticker getStickerByStickerId(Integer stickerId);

	StickerSummaryList getAllStickerList();

	Integer createSticker(CreateStickerInfo request);

	StickerSummaryList getHotCustomStickerList();

	StickerInfo getOneStickerInfo(Integer stickerId);

	StickerSummaryList getSimilarStickerList(Integer stickerId);

	StickerSummaryList getSearchStickerList(String word);

	StickerSummaryList getFilterStickerList(String sort, String theme, String style);

	void addStickerListByThemeList(List<Sticker> stickers, List<Theme> themes);

	void addStickerListByStyleList(List<Sticker> stickers, List<Style> styles);

	void addStickerListByTheme(List<Sticker> stickers, Theme theme);

	void addStickerListByStyle(List<Sticker> stickers, Style style);
}

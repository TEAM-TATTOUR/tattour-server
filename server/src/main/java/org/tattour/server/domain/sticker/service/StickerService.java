package org.tattour.server.domain.sticker.service;

import java.util.List;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.theme.domain.Theme;

public interface StickerService {

	Sticker save(Sticker sticker);

	void addStickerListByThemeList(List<Sticker> stickers, List<Theme> themes);

	void addStickerListByStyleList(List<Sticker> stickers, List<Style> styles);

	void addStickerListByTheme(List<Sticker> stickers, Theme theme);

	void addStickerListByStyle(List<Sticker> stickers, Style style);
}

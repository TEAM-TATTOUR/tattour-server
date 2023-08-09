package org.tattour.server.domain.sticker.repository;

import java.util.List;
import org.tattour.server.domain.sticker.domain.StickerTheme;

public interface StickerThemeRepository {

	StickerTheme save(StickerTheme stickerTheme);

	List<StickerTheme> findAllBySticker_Id(Integer stickerId);

	List<StickerTheme> findAllByTheme_Id(Integer themeId);
}

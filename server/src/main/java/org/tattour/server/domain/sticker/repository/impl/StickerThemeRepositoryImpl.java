package org.tattour.server.domain.sticker.repository.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.sticker.domain.StickerTheme;

@Repository
public interface StickerThemeRepositoryImpl extends JpaRepository<StickerTheme, Integer> {

	List<StickerTheme> findAllBySticker_Id(Integer stickerId);
	List<StickerTheme> findAllByTheme_Id(Integer themeId);
}

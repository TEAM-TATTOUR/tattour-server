package org.tattour.server.domain.sticker.repository.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.sticker.domain.StickerStyle;

@Repository
public interface StickerStyleRepositoryImpl extends JpaRepository<StickerStyle, Integer> {

	List<StickerStyle> findAllBySticker_Id(Integer stickerId);

	List<StickerStyle> findAllByStyle_Id(Integer styleId);
}

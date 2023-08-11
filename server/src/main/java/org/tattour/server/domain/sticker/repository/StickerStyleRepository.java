package org.tattour.server.domain.sticker.repository;

import java.util.List;
import org.tattour.server.domain.sticker.domain.StickerStyle;

public interface StickerStyleRepository {

	StickerStyle save(StickerStyle stickerStyle);

	List<StickerStyle> findAllBySticker_Id(Integer stickerId);

	List<StickerStyle> findAllByStyle_Id(Integer styleId);
}

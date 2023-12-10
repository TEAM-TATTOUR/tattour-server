package org.tattour.server.domain.sticker.service;

import java.util.List;
import org.tattour.server.domain.sticker.model.Sticker;

public interface StickerService {

	Sticker save(Sticker sticker);

	void sortStickerListByNumberOfOrderDesc(List<Sticker> stickers);
}

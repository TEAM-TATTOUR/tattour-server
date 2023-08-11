package org.tattour.server.domain.sticker.repository;

import java.util.List;
import org.tattour.server.domain.sticker.domain.StickerImage;

public interface StickerImageRepository {

	StickerImage save(StickerImage stickerImage);

	List<StickerImage> findAllByStickerId(Integer stickerId);
}

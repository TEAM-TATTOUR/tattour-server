package org.tattour.server.domain.sticker.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.sticker.domain.StickerImage;
import org.tattour.server.domain.sticker.repository.StickerImageRepository;

public interface StickerImageRepositoryImpl extends
	Repository<StickerImage, Integer>,
    StickerImageRepository {

}

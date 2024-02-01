package org.tattour.server.domain.sticker.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.sticker.model.StickerStyle;
import org.tattour.server.domain.sticker.repository.StickerStyleRepository;

public interface StickerStyleRepositoryImpl extends
    Repository<StickerStyle, Integer>,
    StickerStyleRepository {

}

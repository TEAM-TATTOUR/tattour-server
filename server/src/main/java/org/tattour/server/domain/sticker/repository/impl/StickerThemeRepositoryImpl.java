package org.tattour.server.domain.sticker.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.sticker.domain.StickerTheme;
import org.tattour.server.domain.sticker.repository.StickerThemeRepository;

public interface StickerThemeRepositoryImpl extends
    Repository<StickerTheme, Integer>,
    StickerThemeRepository {

}

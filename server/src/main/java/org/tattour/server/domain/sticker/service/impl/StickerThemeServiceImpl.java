package org.tattour.server.domain.sticker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.model.StickerTheme;
import org.tattour.server.domain.sticker.repository.StickerThemeRepository;
import org.tattour.server.domain.sticker.service.StickerThemeService;

@Service
@RequiredArgsConstructor
public class StickerThemeServiceImpl implements StickerThemeService {

	private final StickerThemeRepository stickerThemeRepository;

	@Override
	public StickerTheme save(StickerTheme stickerTheme) {
		return stickerThemeRepository.save(stickerTheme);
	}
}

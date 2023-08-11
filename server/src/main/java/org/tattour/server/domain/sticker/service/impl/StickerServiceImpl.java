package org.tattour.server.domain.sticker.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.repository.StickerRepository;
import org.tattour.server.domain.sticker.service.StickerService;
import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.theme.domain.Theme;

@Service
@RequiredArgsConstructor
public class StickerServiceImpl implements StickerService {

	private final StickerRepository stickerRepository;

	@Override
	public Sticker save(Sticker sticker) {
		return stickerRepository.save(sticker);
	}

	@Override
	public void addStickerListByThemeList(List<Sticker> stickers, List<Theme> themes) {
		for (Theme theme : themes) {
			addStickerListByTheme(stickers, theme);
		}
	}

	@Override
	public void addStickerListByStyleList(List<Sticker> stickers, List<Style> styles) {
		for (Style style : styles) {
			addStickerListByStyle(stickers, style);
		}
	}

	@Override
	public void addStickerListByTheme(List<Sticker> stickers, Theme theme) {
		List<Sticker> findStickers = theme.getStickerThemes().stream()
			.map(stickerTheme -> stickerTheme.getSticker())
			.collect(Collectors.toList());
		for (Sticker sticker : findStickers) {
			if (!stickers.contains(sticker)) {
				stickers.add(sticker);
			}
		}
	}

	@Override
	public void addStickerListByStyle(List<Sticker> stickers, Style style) {
		List<Sticker> findStickers = style.getStickerStyles().stream()
			.map(stickerStyle -> stickerStyle.getSticker())
			.collect(Collectors.toList());
		for (Sticker sticker : findStickers) {
			if (!stickers.contains(sticker)) {
				stickers.add(sticker);
			}
		}
	}
}

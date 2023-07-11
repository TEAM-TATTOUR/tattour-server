package org.tattour.server.domain.search.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.repository.impl.StickerRepositoryImpl;
import org.tattour.server.domain.sticker.service.StickerService;
import org.tattour.server.domain.sticker.service.dto.response.StickerSummaryListRes;
import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.style.repository.impl.StyleRepositoryImpl;
import org.tattour.server.domain.theme.domain.Theme;
import org.tattour.server.domain.theme.repository.impl.ThemeRepositoryImpl;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final StickerService stickerService;
	private final StickerRepositoryImpl stickerRepository;
	private final ThemeRepositoryImpl themeRepository;
	private final StyleRepositoryImpl styleRepository;

	@Override
	public StickerSummaryListRes searchSticker(String word) {
		List<Sticker> result = stickerRepository.findByNameContaining(word);
		List<Theme> themes = themeRepository.findByNameLike(word);
		List<Style> styles = styleRepository.findByNameLike(word);
		stickerService.addStickerListByThemeList(result, themes);
		stickerService.addStickerListByStyleList(result, styles);
		return StickerSummaryListRes.of(result);
	}
}

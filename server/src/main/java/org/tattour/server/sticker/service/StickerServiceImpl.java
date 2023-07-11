package org.tattour.server.sticker.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.sticker.domain.Sticker;
import org.tattour.server.sticker.domain.StickerImage;
import org.tattour.server.sticker.domain.StickerSort;
import org.tattour.server.sticker.exception.NotFoundStickerException;
import org.tattour.server.sticker.repository.impl.StickerImageRepositoryImpl;
import org.tattour.server.sticker.repository.impl.StickerRepositoryImpl;
import org.tattour.server.sticker.service.dto.response.StickerInfoRes;
import org.tattour.server.sticker.service.dto.response.StickerSummaryListRes;
import org.tattour.server.style.domain.Style;
import org.tattour.server.style.exception.NotFoundStyleException;
import org.tattour.server.style.repository.impl.StyleRepositoryImpl;
import org.tattour.server.theme.domain.Theme;
import org.tattour.server.theme.exception.NotFoundThemeException;
import org.tattour.server.theme.repository.impl.ThemeRepositoryImpl;

@Service
@RequiredArgsConstructor
public class StickerServiceImpl implements StickerService {

	private final StickerRepositoryImpl stickerRepository;
	private final StickerImageRepositoryImpl stickerImageRepository;
	private final ThemeRepositoryImpl themeRepository;
	private final StyleRepositoryImpl styleRepository;

	@Override
	public Sticker getStickerByStickerId(Integer stickerId) {
		 Sticker sticker = stickerRepository.findById(stickerId)
			.orElseThrow(NotFoundStickerException::new);
		return sticker;
	}

	@Override
	public StickerInfoRes getOneStickerInfo(Integer stickerId) {
		Sticker sticker = stickerRepository.findById(stickerId)
			.orElseThrow(NotFoundStickerException::new);
		List<StickerImage> images = stickerImageRepository.findAllByStickerId(stickerId);

		return StickerInfoRes.from(sticker, images);
	}

	@Override
	public StickerSummaryListRes getAllStickerList() {
		List<Sticker> stickers = stickerRepository.findAllByStateTrue();
		return StickerSummaryListRes.of(stickers);
	}

	@Override
	public StickerSummaryListRes getHotCustomStickerList() {
		List<Sticker> stickers = stickerRepository.findAllByIsCustomTrueAndStateTrue();
		return StickerSummaryListRes.of(stickers);
	}

	@Override
	public StickerSummaryListRes getSimilarStickerList(Integer stickerId) {
		List<Sticker> result = new ArrayList<>();
		Sticker sticker = getStickerByStickerId(stickerId);
		List<Theme> themes = sticker.getStickerThemes().stream()
			.map(stickerTheme -> stickerTheme.getTheme())
			.collect(Collectors.toList());
		List<Style> styles = sticker.getStickerStyles().stream()
			.map(stickerStyle -> stickerStyle.getStyle())
			.collect(Collectors.toList());
		addStickerListByThemeList(result, themes);
		addStickerListByStyleList(result, styles);
		result.remove(sticker);
		return StickerSummaryListRes.of(result);
	}

	@Override
	public StickerSummaryListRes getSearchStickerList(String word) {
		return null;
	}

	@Override
	public StickerSummaryListRes getFilterStickerList(String sort, String theme, String style) {
		List<Sticker> result = new ArrayList<>();
		StickerSort stickerSort = getStickerSort(sort);
		if (theme.isEmpty() && theme.isEmpty()) {
			result = stickerRepository.findAll();
			sortStickerListByStickerSort(result, stickerSort);
			return StickerSummaryListRes.of(result);
		}
		if (style.isEmpty()) {
			Theme fileterTheme = themeRepository.findByName(theme)
				.orElseThrow(NotFoundThemeException::new);
			addStickerListByTheme(result, fileterTheme);
			sortStickerListByStickerSort(result, stickerSort);
			return StickerSummaryListRes.of(result);
		}
		if (theme.isEmpty()) {
			Style filterStyle = styleRepository.findByName(style)
				.orElseThrow(NotFoundStyleException::new);
			addStickerListByStyle(result, filterStyle);
			sortStickerListByStickerSort(result, stickerSort);
			return StickerSummaryListRes.of(result);
		}
		Theme fileterTheme = themeRepository.findByName(theme)
			.orElseThrow(NotFoundThemeException::new);
		Style filterStyle = styleRepository.findByName(style)
			.orElseThrow(NotFoundStyleException::new);
		addStickerListByTheme(result, fileterTheme);
		addStickerListByStyle(result, filterStyle);
		sortStickerListByStickerSort(result, stickerSort);
		return StickerSummaryListRes.of(result);
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

	private void sortStickerListByStickerSort(List<Sticker> stickers, StickerSort sort) {
		switch (sort) {
			case POPULARITY:
				break;
			case PRICE_HIGH:
				Collections.sort( stickers, (o1,o2) -> o2.getPrice() - o1.getPrice() );
			case PRICE_LOW:
				Collections.sort( stickers, (o1,o2) -> o1.getPrice() - o2.getPrice() );
		}
	}

	private StickerSort getStickerSort(String sort) {
		String upperCaseSort = sort.toUpperCase();
		try {
			return StickerSort.valueOf(upperCaseSort);
		} catch (Exception e) {
			throw new ConstraintViolationException(new HashSet<>());
		}
	}
}

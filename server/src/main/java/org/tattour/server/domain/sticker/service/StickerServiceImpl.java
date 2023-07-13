package org.tattour.server.domain.sticker.service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.domain.StickerImage;
import org.tattour.server.domain.sticker.domain.StickerSort;
import org.tattour.server.domain.sticker.repository.impl.StickerImageRepositoryImpl;
import org.tattour.server.domain.sticker.repository.impl.StickerRepositoryImpl;
import org.tattour.server.domain.sticker.service.dto.response.StickerInfo;
import org.tattour.server.domain.sticker.service.dto.response.StickerSummaryList;
import org.tattour.server.domain.sticker.exception.NotFoundStickerException;
import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.style.exception.NotFoundStyleException;
import org.tattour.server.domain.style.repository.impl.StyleRepositoryImpl;
import org.tattour.server.domain.theme.domain.Theme;
import org.tattour.server.domain.theme.exception.NotFoundThemeException;
import org.tattour.server.domain.theme.repository.impl.ThemeRepositoryImpl;

@Service
@RequiredArgsConstructor
public class StickerServiceImpl implements StickerService {

	private final StickerRepositoryImpl stickerRepository;
	private final StickerImageRepositoryImpl stickerImageRepository;
	private final ThemeRepositoryImpl themeRepository;
	private final StyleRepositoryImpl styleRepository;

	@Override
	@Transactional(readOnly = true)
	public Sticker getStickerByStickerId(Integer stickerId) {
		 Sticker sticker = stickerRepository.findById(stickerId)
			.orElseThrow(NotFoundStickerException::new);
		return sticker;
	}

	@Override
	@Transactional(readOnly = true)
	public StickerInfo getOneStickerInfo(Integer stickerId) {
		Sticker sticker = stickerRepository.findById(stickerId)
			.orElseThrow(NotFoundStickerException::new);
		List<StickerImage> images = stickerImageRepository.findAllByStickerId(stickerId);

		return StickerInfo.from(sticker, images);
	}

	@Override
	@Transactional(readOnly = true)
	public StickerSummaryList getAllStickerList() {
		List<Sticker> stickers = stickerRepository.findAllByStateTrue();
		return StickerSummaryList.of(stickers);
	}

	@Override
	@Transactional(readOnly = true)
	public StickerSummaryList getHotCustomStickerList() {
		List<Sticker> stickers = stickerRepository.findAllByIsCustomTrueAndStateTrue();
		return StickerSummaryList.of(stickers);
	}

	@Override
	@Transactional(readOnly = true)
	public StickerSummaryList getSimilarStickerList(Integer stickerId) {
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
		return StickerSummaryList.of(result);
	}

	@Override
	public StickerSummaryList getSearchStickerList(String word) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public StickerSummaryList getFilterStickerList(String sort, String theme, String style) {
		List<Sticker> result = new ArrayList<>();
		StickerSort stickerSort = StickerSort.getStickerSort(sort);
		if (theme.isEmpty() && theme.isEmpty()) {
			result = stickerRepository.findAll();
			sortStickerListByStickerSort(result, stickerSort);
			return StickerSummaryList.of(result);
		}
		if (style.isEmpty()) {
			Theme fileterTheme = themeRepository.findByName(theme)
				.orElseThrow(NotFoundThemeException::new);
			addStickerListByTheme(result, fileterTheme);
			sortStickerListByStickerSort(result, stickerSort);
			return StickerSummaryList.of(result);
		}
		if (theme.isEmpty()) {
			Style filterStyle = styleRepository.findByName(style)
				.orElseThrow(NotFoundStyleException::new);
			addStickerListByStyle(result, filterStyle);
			sortStickerListByStickerSort(result, stickerSort);
			return StickerSummaryList.of(result);
		}
		Theme fileterTheme = themeRepository.findByName(theme)
			.orElseThrow(NotFoundThemeException::new);
		Style filterStyle = styleRepository.findByName(style)
			.orElseThrow(NotFoundStyleException::new);
		addStickerListByTheme(result, fileterTheme);
		addStickerListByStyle(result, filterStyle);
		sortStickerListByStickerSort(result, stickerSort);
		return StickerSummaryList.of(result);
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
}

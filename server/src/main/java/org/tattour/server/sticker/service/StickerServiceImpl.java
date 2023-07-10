package org.tattour.server.sticker.service;


import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.sticker.domain.Sticker;
import org.tattour.server.sticker.domain.StickerImage;
import org.tattour.server.sticker.domain.StickerStyle;
import org.tattour.server.sticker.domain.StickerTheme;
import org.tattour.server.sticker.exception.NotFoundStickerException;
import org.tattour.server.sticker.repository.impl.StickerImageRepositoryImpl;
import org.tattour.server.sticker.repository.impl.StickerRepositoryImpl;
import org.tattour.server.sticker.repository.impl.StickerStyleRepositoryImpl;
import org.tattour.server.sticker.repository.impl.StickerThemeRepositoryImpl;
import org.tattour.server.sticker.service.dto.response.StickerInfoRes;
import org.tattour.server.sticker.service.dto.response.StickerSummaryListRes;

@Service
@RequiredArgsConstructor
public class StickerServiceImpl implements StickerService {

	private final StickerRepositoryImpl stickerRepository;
	private final StickerImageRepositoryImpl stickerImageRepository;
	private final StickerThemeRepositoryImpl stickerThemeRepository;
	private final StickerStyleRepositoryImpl stickerStyleRepository;


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
		Sticker sticker = stickerRepository.findByIdAndStateTrue(stickerId)
			.orElseThrow(NotFoundStickerException::new);
		List<StickerTheme> stickerThemes = stickerThemeRepository.findAllBySticker_Id(stickerId);
		List<StickerStyle> stickerStyles = stickerStyleRepository.findAllBySticker_Id(stickerId);
		List<Sticker> result = new ArrayList<>();
		addStickerFromStickerThemes(result, stickerThemes);
		addStickerFromStickerStyles(result, stickerStyles);
		result.remove(sticker);
		return StickerSummaryListRes.of(result);
	}

	private void addStickerFromStickerThemes(List<Sticker> stickers, List<StickerTheme> stickerThemes) {
		for (StickerTheme stickerTheme : stickerThemes) {
			List<StickerTheme> resultThemes = stickerThemeRepository.findAllByTheme_Id(
				stickerTheme.getTheme().getId());
			for (StickerTheme theme : resultThemes) {
				Sticker findSticker = stickerRepository.findById(theme.getSticker().getId())
					.orElseThrow();
				if (!stickers.contains(findSticker) && findSticker.getState()) {
					stickers.add(findSticker);
				}
			}
		}
	}


	private void addStickerFromStickerStyles(List<Sticker> stickers, List<StickerStyle> stickerStyles) {
		for (StickerStyle stickerStyle : stickerStyles) {
			List<StickerStyle> resultStyles = stickerStyleRepository.findAllByStyle_Id(
				stickerStyle.getSticker().getId());
			for (StickerStyle style : resultStyles) {
				Sticker findSticker = stickerRepository.findById(style.getSticker().getId())
					.orElseThrow();
				if (!stickers.contains(findSticker) && findSticker.getState()) {
					stickers.add(findSticker);
				}
			}
		}
	}


	@Override
	public StickerSummaryListRes getSearchStickerList(String word) {
		return null;
	}

	@Override
	public StickerSummaryListRes getFilterStickerList(String order, String theme, String style) {
		return null;
	}

	@Override
	public StickerInfoRes getOneStickerInfo(Integer stickerId) {
		Sticker sticker = stickerRepository.findById(stickerId)
			.orElseThrow(NotFoundStickerException::new);
		List<StickerImage> images = stickerImageRepository.findAllByStickerId(stickerId);

		return StickerInfoRes.from(sticker, images);
	}
}

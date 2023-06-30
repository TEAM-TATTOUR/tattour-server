package org.tattour.server.sticker.service;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.sticker.domain.Sticker;
import org.tattour.server.sticker.domain.StickerImage;
import org.tattour.server.sticker.exception.NotFoundStickerException;
import org.tattour.server.sticker.repository.impl.StickerImageRepositoryImpl;
import org.tattour.server.sticker.repository.impl.StickerRepositoryImpl;
import org.tattour.server.sticker.service.dto.response.StickerInfoRes;
import org.tattour.server.sticker.service.dto.response.StickerSummaryListRes;

@Service
@RequiredArgsConstructor
public class StickerServiceImpl implements StickerService {

	private final StickerRepositoryImpl stickerRepository;
	private final StickerImageRepositoryImpl stickerImageRepository;

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
		return null;
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

package org.tattour.server.domain.sticker.provider.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.exception.NotFoundStickerException;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.sticker.repository.StickerRepository;
import org.tattour.server.domain.sticker.provider.vo.ReadOrderSheetStickerInfo;

@Slf4j
@Service
@RequiredArgsConstructor
public class StickerProviderImpl implements StickerProvider {

	private final StickerRepository stickerRepository;

	@Override
	public Sticker getById(Integer id) {
		return stickerRepository.findById(id)
			.orElseThrow(NotFoundStickerException::new);
	}

	@Override
	public List<Sticker> getAll() {
		return stickerRepository.findAll();
	}

	@Override
	public List<Sticker> getAllByStateTrue() {
		return stickerRepository.findAllByStateTrue();
	}

	@Override
	public List<Sticker> getAllCustomStickerOrderByOrder() {
		return stickerRepository.findAllByStateAndIsCustomInOrderOrder();
	}

	@Override
	public List<Sticker> getAllSameThemeOrStyleBySticker(Integer id) {
		log.info("1111111111111111111");
		List<Sticker> result = stickerRepository.findAllSameThemeOrStyleById(id);
		log.info("2222222222222222222");
		return result;
	}

	@Override
	public List<Sticker> getAllByNameLike(String name) {
		return stickerRepository.findByNameContaining(name);
	}

	// Todo : 리펙토링하기
	@Override
	public ReadOrderSheetStickerInfo readOrderSheetStickerInfo(Sticker sticker) {
		Integer discountedPrice = getDiscountPrice(sticker);

		return ReadOrderSheetStickerInfo.of(
			sticker.getMainImageUrl(),
			sticker.getName(),
			sticker.getPrice(),
			discountedPrice);
	}

	private static Integer getDiscountPrice(Sticker sticker) {
		if (Objects.isNull(sticker.getDiscount())) {
			return null;
		}
		return (sticker.getPrice() * (100 - sticker.getDiscount().getDiscountRate())) / 100;
	}
}

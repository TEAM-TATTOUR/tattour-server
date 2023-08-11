package org.tattour.server.domain.sticker.provider.impl;

import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.exception.NotFoundStickerException;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.sticker.repository.StickerRepository;
import org.tattour.server.domain.sticker.provider.vo.ReadOrderSheetStickerInfo;

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
	public List<Sticker> getAllByIsCustomTrueAndStateTrue() {
		return stickerRepository.findAllByIsCustomTrueAndStateTrue();
	}

	@Override
	public List<Sticker> getAllByNameLike(String name) {
		return stickerRepository.findByNameContaining(name);
	}

	// Todo : 리펙토링하기
	@Override
	public ReadOrderSheetStickerInfo readOrderSheetStickerInfo(Integer stickerId) {
		Sticker sticker = getById(stickerId);
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

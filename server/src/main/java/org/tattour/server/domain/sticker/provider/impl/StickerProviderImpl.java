package org.tattour.server.domain.sticker.provider.impl;

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
	public List<Sticker> getAllCustomStickerOrderByOrder() {
		return stickerRepository
				.findAllByStateAndIsCustomInOrderOrder();
	}

	@Override
	public List<Sticker> getAllByThemeAndStyleOrderByOrder(String theme, String style) {
		return stickerRepository
				.findAllByThemeNameAndStyleNameAndStateInOrderOrder(theme, style);
	}

	@Override
	public List<Sticker> getAllByThemeAndStyleOrderByPrice(String theme, String style) {
		return stickerRepository
				.findAllByThemeNameAndStyleNameAndStateInOrderPrice(theme, style);
	}

	@Override
	public List<Sticker> getAllByThemeAndStyleOrderByPriceDesc(String theme, String style) {
		return stickerRepository
				.findAllByThemeNameAndStyleNameAndStateInOrderPriceDesc(theme, style);
	}

	@Override
	public List<Sticker> getAllSameThemeOrStyleById(Integer id) {
		return stickerRepository
				.findAllSameThemeOrStyleById(id);
	}

	@Override
	public List<Sticker> getAllByThemeOrStyleOrNameLike(String word) {
		if(Objects.isNull(word)) {
			return null;
		}
		return stickerRepository
				.findAllByThemeNameOrStyleNameOrNameContaining(word);
	}

	// Todo : 리펙토링하기  of -> from?
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

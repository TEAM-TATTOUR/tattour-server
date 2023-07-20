package org.tattour.server.domain.discount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.discount.domain.Discount;
import org.tattour.server.domain.discount.exception.NotFoundDiscountException;
import org.tattour.server.domain.discount.repository.impl.DiscountRepositoryImpl;
import org.tattour.server.domain.discount.service.dto.request.DiscountInfo;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.service.StickerService;
import org.tattour.server.domain.sticker.service.dto.response.StickerInfo;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {

	private final DiscountRepositoryImpl discountRepository;
	private final StickerService stickerService;

	@Override
	public Discount getDiscountByDiscountId(Integer discountId) {
		return discountRepository.findById(discountId)
				.orElseThrow(NotFoundDiscountException::new);
	}

	@Override
	@Transactional
	public DiscountInfo createDiscount(DiscountInfo discountInfo) {
		Discount discount = Discount.from(discountInfo.getName(),
				discountInfo.getDiscountRate(),
				discountInfo.getStartAt(),
				discountInfo.getEndedAt(),
				false);
		discountRepository.save(discount);
		return DiscountInfo.of(discount);
	}

	@Override
	@Transactional
	public StickerInfo applyStickerDiscount(Integer stickerId, Integer discountId) {
		Sticker sticker = stickerService.getStickerByStickerId(stickerId);
		Discount discount = getDiscountByDiscountId(discountId);
		sticker.applyDiscount(discount);
		return StickerInfo.from(sticker);
	}
}

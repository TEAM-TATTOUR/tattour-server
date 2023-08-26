package org.tattour.server.domain.discount.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.discount.domain.Discount;
import org.tattour.server.domain.discount.provider.DiscountProvider;
import org.tattour.server.domain.discount.repository.DiscountRepository;
import org.tattour.server.domain.discount.facade.DiscountFacade;
import org.tattour.server.domain.discount.facade.dto.request.CreateDiscountReq;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerRes;
import org.tattour.server.domain.sticker.provider.StickerProvider;

@Service
@RequiredArgsConstructor
public class DiscountFacadeImpl implements DiscountFacade {

	private final DiscountRepository discountRepository;
	private final DiscountProvider discountProvider;
	private final StickerProvider stickerProvider;

	@Override
	@Transactional
	public void createDiscount(CreateDiscountReq createDiscountReq) {
		Discount discount = Discount.of(createDiscountReq.getName(),
				createDiscountReq.getDiscountRate(),
				createDiscountReq.getStartAt(),
				createDiscountReq.getEndedAt(),
				false);
		discountRepository.save(discount);
	}

	@Override
	@Transactional
	public ReadStickerRes applyStickerDiscount(Integer stickerId, Integer discountId) {
		Sticker sticker = stickerProvider.getById(stickerId);
		Discount discount = discountProvider.getById(discountId);
		sticker.applyDiscount(discount);
		return ReadStickerRes.from(sticker);
	}
}

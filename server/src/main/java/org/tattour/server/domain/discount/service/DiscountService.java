package org.tattour.server.domain.discount.service;

import org.tattour.server.domain.discount.domain.Discount;
import org.tattour.server.domain.discount.service.dto.request.DiscountInfo;
import org.tattour.server.domain.sticker.service.dto.response.StickerInfo;

public interface DiscountService {

	Discount getDiscountByDiscountId(Integer discountId);

	DiscountInfo createDiscount(DiscountInfo discountInfo);

	StickerInfo applyStickerDiscount(Integer stickerId, Integer discountId);
}

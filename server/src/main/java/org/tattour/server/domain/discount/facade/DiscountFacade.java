package org.tattour.server.domain.discount.facade;

import org.tattour.server.domain.discount.facade.dto.request.CreateDiscountReq;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerRes;

public interface DiscountFacade {

    void createDiscount(CreateDiscountReq createDiscountReq);

    ReadStickerRes applyStickerDiscount(Integer stickerId, Integer discountId);
}

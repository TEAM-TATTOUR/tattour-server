package org.tattour.server.domain.discount.provider;

import org.tattour.server.domain.discount.domain.Discount;

public interface DiscountProvider {

	Discount getById(Integer discountId);
}

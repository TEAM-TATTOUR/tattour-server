package org.tattour.server.domain.discount.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tattour.server.domain.discount.model.Discount;
import org.tattour.server.domain.discount.exception.NotFoundDiscountException;
import org.tattour.server.domain.discount.provider.DiscountProvider;
import org.tattour.server.domain.discount.repository.DiscountRepository;

@Component
@RequiredArgsConstructor
public class DiscountProviderImpl implements DiscountProvider {

	private final DiscountRepository discountRepository;

	@Override
	public Discount getById(Integer discountId) {
		return discountRepository.findById(discountId)
			.orElseThrow(NotFoundDiscountException::new);
	}
}

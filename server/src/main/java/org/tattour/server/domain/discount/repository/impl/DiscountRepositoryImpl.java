package org.tattour.server.domain.discount.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.discount.model.Discount;
import org.tattour.server.domain.discount.repository.DiscountRepository;

public interface DiscountRepositoryImpl extends
	Repository<Discount, Integer>,
	DiscountRepository {

}

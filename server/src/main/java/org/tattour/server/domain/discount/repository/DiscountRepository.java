package org.tattour.server.domain.discount.repository;

import java.util.Optional;
import org.tattour.server.domain.discount.domain.Discount;

public interface DiscountRepository {

	Optional<Discount> findById(Integer id);

	Discount save(Discount discount);
}

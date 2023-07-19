package org.tattour.server.domain.discount.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tattour.server.domain.discount.domain.Discount;

public interface DiscountRepositoryImpl extends JpaRepository<Discount, Integer> {

}

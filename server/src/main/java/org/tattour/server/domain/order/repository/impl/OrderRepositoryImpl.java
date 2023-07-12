package org.tattour.server.domain.order.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.order.domain.Order;
@Repository
public interface OrderRepositoryImpl extends JpaRepository<Order, Integer> {

}

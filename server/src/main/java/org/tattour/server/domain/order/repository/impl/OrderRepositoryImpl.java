package org.tattour.server.domain.order.repository.impl;

import java.awt.print.Pageable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.order.domain.Order;
@Repository
public interface OrderRepositoryImpl extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser_Id(Integer userId);
}

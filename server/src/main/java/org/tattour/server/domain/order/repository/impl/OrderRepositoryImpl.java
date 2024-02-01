package org.tattour.server.domain.order.repository.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.order.model.OrderHistory;

@Repository
public interface OrderRepositoryImpl extends JpaRepository<OrderHistory, Integer> {

    List<OrderHistory> findAllByUser_Id(Integer userId);

    // userId와 기준 시간 이후 행 가져오기
    List<OrderHistory> findAllByUser_IdAndCreatedAtAfter(Integer userId, String date);
}

package org.tattour.server.domain.order.repository.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.order.domain.Order;
@Repository
public interface OrderRepositoryImpl extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser_Id(Integer userId);

    // userId와 기준 시간 이후 행 가져오기
    //TODO : 잘되는지 확인
    List<Order> findAllByUser_IdAndCreatedAtAfter(Integer userId, String date);
}

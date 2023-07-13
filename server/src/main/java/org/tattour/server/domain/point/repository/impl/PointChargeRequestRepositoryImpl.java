package org.tattour.server.domain.point.repository.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.point.domain.PointChargeRequest;
@Repository
public interface PointChargeRequestRepositoryImpl extends JpaRepository<PointChargeRequest, Integer> {
    List<PointChargeRequest> findPointChargeRequestByUser_IdAndCreatedAtAfter(Integer userId, String date);
}

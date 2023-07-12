package org.tattour.server.domain.point.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.point.domain.UserPointLog;

@Repository
public interface UserPointLogRepositoryImpl extends JpaRepository<UserPointLog, Integer> {

}

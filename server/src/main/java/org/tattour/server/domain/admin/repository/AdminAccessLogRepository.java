package org.tattour.server.domain.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.admin.domain.AdminAccessLog;

@Repository
public interface AdminAccessLogRepository extends JpaRepository<AdminAccessLog, Integer> {

}

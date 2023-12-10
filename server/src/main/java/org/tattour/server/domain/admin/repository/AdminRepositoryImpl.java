package org.tattour.server.domain.admin.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.admin.model.Admin;

@Repository
public interface AdminRepositoryImpl extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByAdminName(String adminName);
}

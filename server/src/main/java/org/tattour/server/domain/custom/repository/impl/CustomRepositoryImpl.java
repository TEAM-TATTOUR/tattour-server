package org.tattour.server.domain.custom.repository.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tattour.server.domain.custom.domain.Custom;

public interface CustomRepositoryImpl extends JpaRepository<Custom, Integer> {

	List<Custom> findAllByUser_IdAndIsCompletedTrue(Integer userId);
	List<Custom> findAllByUser_IdAndIsCompletedFalse(Integer userId);
}

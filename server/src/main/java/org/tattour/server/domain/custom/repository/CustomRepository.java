package org.tattour.server.domain.custom.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.repository.custom.CustomRepositoryCustom;

public interface CustomRepository extends
		Repository<Custom, Integer>,
		CustomRepositoryCustom {

	Custom save(Custom custom);

	Optional<Custom> findById(Integer id);

	List<Custom> findAllByUser_IdAndCreatedAtAfter(Integer userId, String date);
}

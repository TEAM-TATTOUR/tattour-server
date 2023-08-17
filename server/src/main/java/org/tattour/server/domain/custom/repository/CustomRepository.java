package org.tattour.server.domain.custom.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.tattour.server.domain.custom.domain.Custom;

public interface CustomRepository {

	Custom save(Custom custom);

	Optional<Custom> findById(Integer id);

	@Query("select c "
			+ "from Custom c "
			+ "where c.user.id = :userId "
			+ "and c.isCompleted = true")
	List<Custom> findAllByUserIdAndIsCompleted(@Param("userId") Integer userId);

	@Query("select c "
			+ "from Custom c "
			+ "where c.user.id = :userId "
			+ "and c.isCompleted = false")
	List<Custom> findAllByUserIdAndIsCompletedFalse(Integer userId);

	List<Custom> findAllByUser_IdAndCreatedAtAfter(Integer userId, String date);
}

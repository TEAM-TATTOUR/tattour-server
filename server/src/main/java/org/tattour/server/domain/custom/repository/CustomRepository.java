package org.tattour.server.domain.custom.repository;

import java.util.List;
import java.util.Optional;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.order.domain.Order;

public interface CustomRepository {

	Custom save(Custom custom);

	Optional<Custom> findById(Integer id);

	List<Custom> findAllByUser_IdAndIsCompletedTrue(Integer userId);

	List<Custom> findAllByUser_IdAndIsCompletedFalse(Integer userId);

	List<Custom> findAllByUser_IdAndCreatedAtAfter(Integer userId, String date);
}

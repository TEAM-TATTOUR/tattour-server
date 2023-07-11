package org.tattour.server.style.repository.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tattour.server.style.domain.Style;

public interface StyleRepositoryImpl extends JpaRepository<Style, Integer> {

	Optional<Style> findByName(String name);
	List<Style> findByNameLike(String name);
}

package org.tattour.server.domain.style.repository;

import java.util.List;
import java.util.Optional;
import org.tattour.server.domain.style.domain.Style;

public interface StyleRepository {

	Optional<Style> findById(Integer styleId);

	List<Style> findAll();

	Optional<Style> findByName(String name);

	List<Style> findByNameLike(String name);
}

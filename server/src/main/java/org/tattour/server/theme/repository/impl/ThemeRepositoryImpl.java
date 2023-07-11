package org.tattour.server.theme.repository.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tattour.server.theme.domain.Theme;

public interface ThemeRepositoryImpl extends JpaRepository<Theme, Integer> {

	Optional<Theme> findByName(String name);
	List<Theme> findByNameLike(String name);
}

package org.tattour.server.domain.theme.repository;

import java.util.List;
import java.util.Optional;
import org.tattour.server.domain.theme.domain.Theme;

public interface ThemeRepository {

	Optional<Theme> findById(Integer themeId);

	List<Theme> findAll();
}

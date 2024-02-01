package org.tattour.server.domain.custom.repository;

import java.util.List;
import java.util.Optional;
import org.tattour.server.domain.custom.model.CustomTheme;

public interface CustomThemeRepository {

	CustomTheme save(CustomTheme customTheme);

	List<CustomTheme> saveAll(Iterable<CustomTheme> customTheme);

	Optional<CustomTheme> findByCustomIdAndThemeId(Integer customId, Integer themeId);
}

package org.tattour.server.domain.custom.repository;

import java.util.Optional;
import org.tattour.server.domain.custom.domain.CustomTheme;

public interface CustomThemeRepository {

	CustomTheme save(CustomTheme customTheme);

	Optional<CustomTheme> findByCustomIdAndThemeId(Integer customId, Integer themeId);
}

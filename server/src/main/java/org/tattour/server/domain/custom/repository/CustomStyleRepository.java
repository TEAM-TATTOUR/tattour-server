package org.tattour.server.domain.custom.repository;

import java.util.Optional;
import org.tattour.server.domain.custom.domain.CustomStyle;
import org.tattour.server.domain.custom.domain.CustomTheme;

public interface CustomStyleRepository {

	CustomStyle save(CustomStyle customStyle);

	Optional<CustomTheme> findByCustomIdAndStyleId(Integer customId, Integer styleId);
}

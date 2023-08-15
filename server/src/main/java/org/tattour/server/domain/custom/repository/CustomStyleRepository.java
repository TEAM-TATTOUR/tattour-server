package org.tattour.server.domain.custom.repository;

import java.util.Optional;
import org.tattour.server.domain.custom.domain.CustomStyle;

public interface CustomStyleRepository {

	CustomStyle save(CustomStyle customStyle);

	Optional<CustomStyle> findByCustomIdAndStyleId(Integer customId, Integer styleId);
}

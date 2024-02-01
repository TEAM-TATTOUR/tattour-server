package org.tattour.server.domain.custom.repository;

import java.util.List;
import java.util.Optional;
import org.tattour.server.domain.custom.model.CustomStyle;

public interface CustomStyleRepository {

	CustomStyle save(CustomStyle customStyle);

	List<CustomStyle> saveAll(Iterable<CustomStyle> customStyles);

	Optional<CustomStyle> findByCustomIdAndStyleId(Integer customId, Integer styleId);
}

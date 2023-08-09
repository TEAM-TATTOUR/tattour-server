package org.tattour.server.domain.style.provider;

import java.util.List;
import org.tattour.server.domain.style.domain.Style;

public interface StyleProvider {

	Style getById(Integer id);

	Style getByName(String name);

	List<Style> getAll();

	List<Style> getAllByNameLike(String name);
}

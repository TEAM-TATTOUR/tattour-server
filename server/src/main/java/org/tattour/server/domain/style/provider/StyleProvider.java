package org.tattour.server.domain.style.provider;

import java.util.List;
import org.tattour.server.domain.style.domain.Style;

public interface StyleProvider {

	Style getById(Integer id);

	List<Style> getAll();
}

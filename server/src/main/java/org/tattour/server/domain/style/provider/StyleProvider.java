package org.tattour.server.domain.style.provider;

import java.util.List;
import org.tattour.server.domain.style.model.Style;

public interface StyleProvider {

    Style getById(Integer id);

    List<Style> getAll();
}

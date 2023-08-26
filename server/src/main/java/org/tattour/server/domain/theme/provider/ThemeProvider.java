package org.tattour.server.domain.theme.provider;

import java.util.List;
import org.tattour.server.domain.theme.domain.Theme;


public interface ThemeProvider {

	Theme getById(Integer id);

	List<Theme> getAll();
}

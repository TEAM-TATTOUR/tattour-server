package org.tattour.server.domain.theme.provider;

import java.util.List;
import org.tattour.server.domain.theme.model.Theme;


public interface ThemeProvider {

    Theme getById(Integer id);

    List<Theme> getAll();
}

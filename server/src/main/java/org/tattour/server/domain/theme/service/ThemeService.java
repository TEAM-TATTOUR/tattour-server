package org.tattour.server.domain.theme.service;

import org.tattour.server.domain.theme.domain.Theme;
import org.tattour.server.domain.theme.service.dto.response.ThemeInfoList;
import org.tattour.server.domain.theme.service.dto.response.ThemeSummaryList;

public interface ThemeService {

    Theme getThemeById(Integer themeId);

    ThemeInfoList getAllTheme();

    ThemeSummaryList getAllThemeSummary();
}

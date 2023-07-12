package org.tattour.server.domain.theme.service;

import org.tattour.server.domain.theme.service.dto.response.ThemeInfoList;
import org.tattour.server.domain.theme.service.dto.response.ThemeSummaryList;

public interface ThemeService {

	ThemeInfoList getAllTheme();

	ThemeSummaryList getAllThemeSummary();
}

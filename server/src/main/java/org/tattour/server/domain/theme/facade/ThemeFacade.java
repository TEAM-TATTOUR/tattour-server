package org.tattour.server.domain.theme.facade;

import org.tattour.server.domain.theme.facade.dto.response.ReadThemeListRes;
import org.tattour.server.domain.theme.facade.dto.response.ReadThemeSummaryListRes;

public interface ThemeFacade {

    ReadThemeListRes readAllTheme();

    ReadThemeSummaryListRes readAllThemeSummary();
}

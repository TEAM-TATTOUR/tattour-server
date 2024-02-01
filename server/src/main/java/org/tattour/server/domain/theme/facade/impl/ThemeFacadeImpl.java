package org.tattour.server.domain.theme.facade.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.theme.facade.ThemeFacade;
import org.tattour.server.domain.theme.facade.dto.response.ReadThemeListRes;
import org.tattour.server.domain.theme.facade.dto.response.ReadThemeSummaryListRes;
import org.tattour.server.domain.theme.model.Theme;
import org.tattour.server.domain.theme.provider.ThemeProvider;

@Service
@RequiredArgsConstructor
public class ThemeFacadeImpl implements ThemeFacade {

    private final ThemeProvider themeProvider;

    @Override
    @Transactional(readOnly = true)
    public ReadThemeListRes readAllTheme() {
        List<Theme> themes = themeProvider.getAll();
        return ReadThemeListRes.from(themes);
    }

    @Override
    @Transactional(readOnly = true)
    public ReadThemeSummaryListRes readAllThemeSummary() {
        List<Theme> themes = themeProvider.getAll();
        return ReadThemeSummaryListRes.from(themes);
    }
}

package org.tattour.server.domain.theme.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.theme.domain.Theme;
import org.tattour.server.domain.theme.repository.impl.ThemeRepositoryImpl;
import org.tattour.server.domain.theme.service.dto.response.ThemeInfoList;
import org.tattour.server.domain.theme.service.dto.response.ThemeSummaryList;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

    private final ThemeRepositoryImpl themeRepository;

    @Override
    @Transactional(readOnly = true)
    public Theme getThemeById(Integer themeId) {
        return themeRepository.findById(themeId)
                .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_THEME_EXCEPTION));
    }

    @Override
    @Transactional(readOnly = true)
    public ThemeInfoList getAllTheme() {
        List<Theme> themes = themeRepository.findAll();
        return ThemeInfoList.of(themes);
    }

    @Override
    @Transactional(readOnly = true)
    public ThemeSummaryList getAllThemeSummary() {
        List<Theme> themes = themeRepository.findAll();
        return ThemeSummaryList.of(themes);
    }
}

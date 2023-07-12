package org.tattour.server.domain.theme.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.theme.domain.Theme;
import org.tattour.server.domain.theme.repository.impl.ThemeRepositoryImpl;
import org.tattour.server.domain.theme.service.dto.response.ThemeInfoList;
import org.tattour.server.domain.theme.service.dto.response.ThemeSummaryList;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl implements ThemeService {

	private final ThemeRepositoryImpl themeRepository;

	@Override
	public ThemeInfoList getAllTheme() {
		List<Theme> themes = themeRepository.findAll();
		return ThemeInfoList.of(themes);
	}

	@Override
	public ThemeSummaryList getAllThemeSummary() {
		List<Theme> themes = themeRepository.findAll();
		return ThemeSummaryList.of(themes);
	}
}

package org.tattour.server.domain.theme.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.theme.domain.Theme;
import org.tattour.server.domain.theme.exception.NotFoundThemeException;
import org.tattour.server.domain.theme.provider.ThemeProvider;
import org.tattour.server.domain.theme.repository.ThemeRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThemeProviderImpl implements ThemeProvider {

	private final ThemeRepository themeRepository;

	@Override
	@Transactional(readOnly = true)
	public Theme getById(Integer id) {
		return themeRepository.findById(id)
			.orElseThrow(NotFoundThemeException::new);
	}

	@Override
	public List<Theme> getAll() {
		return themeRepository.findAll();
	}
}

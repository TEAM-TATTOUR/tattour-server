package org.tattour.server.domain.theme.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.theme.domain.Theme;
import org.tattour.server.domain.theme.exception.NotFoundThemeException;
import org.tattour.server.domain.theme.provider.ThemeProvider;
import org.tattour.server.domain.theme.repository.ThemeRepository;

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

	@Override
	public Theme getByName(String name) {
		return themeRepository.findByName(name)
			.orElseThrow(NotFoundThemeException::new);
	}

	@Override
	public List<Theme> getAllByNameLike(String name) {
		return themeRepository.findByNameLike(name);
	}
}

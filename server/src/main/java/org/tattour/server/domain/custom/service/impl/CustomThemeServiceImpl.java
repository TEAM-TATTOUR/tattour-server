package org.tattour.server.domain.custom.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.domain.CustomTheme;
import org.tattour.server.domain.custom.repository.CustomThemeRepository;
import org.tattour.server.domain.custom.service.CustomThemeService;
import org.tattour.server.domain.theme.provider.ThemeProvider;

@Service
@RequiredArgsConstructor
public class CustomThemeServiceImpl implements CustomThemeService {

	private final CustomThemeRepository customThemeRepository;
	private final ThemeProvider themeProvider;

	@Override
	public CustomTheme save(CustomTheme customTheme) {
		return customThemeRepository.save(customTheme);
	}

	@Override
	public List<CustomTheme> saveAll(List<CustomTheme> customThemes) {
		return customThemes
				.stream()
				.filter(this::isUniqueCustomTheme)
				.map(customThemeRepository::save)
				.collect(Collectors.toList());
	}

	@Override
	public List<CustomTheme> saveByCustomAndCustomId(List<CustomTheme> customThemes) {
		return null;
	}

	@Override
	public List<CustomTheme> saveAllByCustomAndThemeIdList(
			Custom custom,
			List<Integer> themeIdList) {
		List<CustomTheme> customThemes =
				themeIdList
						.stream()
						.map(themeId ->
								CustomTheme.of(
										custom,
										themeProvider.getById(themeId)))
						.collect(Collectors.toList());
		return saveAll(customThemes);
	}

	private boolean isUniqueCustomTheme(CustomTheme customTheme) {
		return customThemeRepository
				.findByCustomIdAndThemeId(
						customTheme.getCustom().getId(),
						customTheme.getTheme().getId())
				.isEmpty();
	}
}

package org.tattour.server.domain.custom.service;

import java.util.List;
import org.tattour.server.domain.custom.model.Custom;
import org.tattour.server.domain.custom.model.CustomTheme;

public interface CustomThemeService {

	CustomTheme save(CustomTheme customTheme);

	List<CustomTheme> saveAll(List<CustomTheme> customThemes);

	List<CustomTheme> saveAllByCustomAndThemeIdList(Custom custom, List<Integer> themeIdList);
}

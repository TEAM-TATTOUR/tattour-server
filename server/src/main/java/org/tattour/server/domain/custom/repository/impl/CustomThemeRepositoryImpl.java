package org.tattour.server.domain.custom.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.custom.domain.CustomTheme;
import org.tattour.server.domain.custom.repository.CustomThemeRepository;

public interface CustomThemeRepositoryImpl extends
		Repository<CustomTheme, Integer>,
		CustomThemeRepository {

}

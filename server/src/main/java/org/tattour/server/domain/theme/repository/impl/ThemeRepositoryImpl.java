package org.tattour.server.domain.theme.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.theme.model.Theme;
import org.tattour.server.domain.theme.repository.ThemeRepository;

public interface ThemeRepositoryImpl extends Repository<Theme, Integer>, ThemeRepository {

}

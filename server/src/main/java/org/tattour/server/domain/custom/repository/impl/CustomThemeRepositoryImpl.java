package org.tattour.server.domain.custom.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tattour.server.domain.custom.domain.CustomTheme;

public interface CustomThemeRepositoryImpl extends JpaRepository<CustomTheme, Integer> {

}

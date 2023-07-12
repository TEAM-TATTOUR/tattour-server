package org.tattour.server.domain.theme.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.theme.repository.impl.ThemeRepositoryImpl;

@Service
@RequiredArgsConstructor
public class ThemeServiceImpl {

	private final ThemeRepositoryImpl themeRepository;


}

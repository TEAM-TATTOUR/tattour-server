package org.tattour.server.domain.style.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.style.repository.impl.StyleRepositoryImpl;
import org.tattour.server.domain.style.service.dto.response.StyleInfoList;
import org.tattour.server.domain.style.service.dto.response.StyleSummaryList;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class StyleServiceImpl implements StyleService {

	private final StyleRepositoryImpl styleRepository;

	@Override
	@Transactional(readOnly = true)
	public Style getStyleById(Integer styleId) {
		return styleRepository.findById(styleId)
			.orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_STYLE_EXCEPTION));
	}

	@Override
	@Transactional(readOnly = true)
	public StyleInfoList getAllStyle() {
		List<Style> styles = styleRepository.findAll();
		return StyleInfoList.of(styles);
	}

	@Override
	@Transactional(readOnly = true)
	public StyleSummaryList getAllStyleSummary() {
		List<Style> styles = styleRepository.findAll();
		return StyleSummaryList.of(styles);
	}
}

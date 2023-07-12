package org.tattour.server.domain.style.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.style.repository.impl.StyleRepositoryImpl;
import org.tattour.server.domain.style.service.dto.response.StyleInfoList;
import org.tattour.server.domain.style.service.dto.response.StyleSummaryList;

@Service
@RequiredArgsConstructor
public class StyleServiceImpl implements StyleService {

	private final StyleRepositoryImpl styleRepository;
	
	@Override
	public StyleInfoList getAllStyle() {
		List<Style> styles = styleRepository.findAll();
		return StyleInfoList.of(styles);
	}

	@Override
	public StyleSummaryList getAllStyleSummary() {
		List<Style> styles = styleRepository.findAll();
		return StyleSummaryList.of(styles);
	}


}

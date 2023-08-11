package org.tattour.server.domain.style.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.style.exception.NotFoundStyleException;
import org.tattour.server.domain.style.provider.StyleProvider;
import org.tattour.server.domain.style.repository.StyleRepository;

@Component
@RequiredArgsConstructor
public class StyleProviderImpl implements StyleProvider {

	private final StyleRepository styleRepository;

	@Override
	public Style getById(Integer id) {
		return styleRepository.findById(id)
			.orElseThrow(NotFoundStyleException::new);
	}

	@Override
	public Style getByName(String name) {
		return styleRepository.findByName(name)
			.orElseThrow(NotFoundStyleException::new);
	}

	@Override
	public List<Style> getAll() {
		return styleRepository.findAll();
	}

	@Override
	public List<Style> getAllByNameLike(String name) {
		return styleRepository.findByNameLike(name);
	}
}

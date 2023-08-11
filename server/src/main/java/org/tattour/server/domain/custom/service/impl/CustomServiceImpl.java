package org.tattour.server.domain.custom.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.repository.CustomRepository;
import org.tattour.server.domain.custom.service.CustomService;

@Service
@RequiredArgsConstructor
public class CustomServiceImpl implements CustomService {

	private final CustomRepository customRepository;

	@Override
	public Custom save(Custom custom) {
		return customRepository.save(custom);
	}
}

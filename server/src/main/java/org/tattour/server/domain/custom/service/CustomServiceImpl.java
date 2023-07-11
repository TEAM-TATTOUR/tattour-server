package org.tattour.server.domain.custom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.custom.repository.impl.CustomRepositoryImpl;
import org.tattour.server.domain.custom.service.dto.request.CustomInfo;

@Service
@RequiredArgsConstructor
public class CustomServiceImpl implements CustomService {

	private final CustomRepositoryImpl customRepository;

	@Override
	@Transactional
	public CustomInfo createCustom(CustomInfo customInfo) {

//		customRepository.save()
		return null;
	}
}

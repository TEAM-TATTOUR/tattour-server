package org.tattour.server.custom.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.custom.repository.impl.CustomRepositoryImpl;
import org.tattour.server.custom.service.dto.request.CustomInfo;

@Service
@RequiredArgsConstructor
public class CustomServiceImpl implements CustomService {

	private final CustomRepositoryImpl customRepository;

	@Override
	public CustomInfo createCustom(CustomInfo customInfo) {

//		customRepository.save()
		return null;
	}
}

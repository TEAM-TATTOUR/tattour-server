package org.tattour.server.domain.custom.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.custom.domain.CustomImage;
import org.tattour.server.domain.custom.repository.CustomImageRepository;
import org.tattour.server.domain.custom.service.CustomImageService;

@Service
@RequiredArgsConstructor
public class CustomImageServiceImpl implements CustomImageService {

	private final CustomImageRepository customImageRepository;


	@Override
	public CustomImage save(CustomImage customImage) {
		return customImageRepository.save(customImage);
	}

	@Override
	public List<CustomImage> saveAll(List<CustomImage> customImages) {
		return customImages
				.stream()
				.map(customImageRepository::save)
				.collect(Collectors.toList());
	}
}

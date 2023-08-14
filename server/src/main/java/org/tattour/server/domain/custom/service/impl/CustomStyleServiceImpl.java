package org.tattour.server.domain.custom.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.domain.CustomStyle;
import org.tattour.server.domain.custom.repository.CustomStyleRepository;
import org.tattour.server.domain.custom.service.CustomStyleService;
import org.tattour.server.domain.style.provider.StyleProvider;

@Service
@RequiredArgsConstructor
public class CustomStyleServiceImpl implements CustomStyleService {

	private final CustomStyleRepository customStyleRepository;
	private final StyleProvider styleProvider;


	@Override
	public CustomStyle save(CustomStyle customStyle) {
		return customStyleRepository.save(customStyle);
	}

	@Override
	public List<CustomStyle> saveAll(List<CustomStyle> customStyles) {
		return customStyles
				.stream()
				.filter(this::isUniqueCustomStyle)
				.map(customStyleRepository::save)
				.collect(Collectors.toList());
	}

	@Override
	public List<CustomStyle> saveByCustomAndStyleIdList(Custom custom, List<Integer> styleIdList) {
		List<CustomStyle> customStyles =
				styleIdList
						.stream()
						.map(styleId ->
								CustomStyle.of(
										custom,
										styleProvider.getById(styleId)))
						.collect(Collectors.toList());
		return saveAll(customStyles);
	}

	private boolean isUniqueCustomStyle(CustomStyle customStyle) {
		return customStyleRepository
				.findByCustomIdAndStyleId(
						customStyle.getCustom().getId(),
						customStyle.getStyle().getId())
				.isEmpty();
	}
}

package org.tattour.server.domain.custom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.domain.CustomImage;
import org.tattour.server.domain.custom.domain.CustomStyle;
import org.tattour.server.domain.custom.domain.CustomTheme;
import org.tattour.server.domain.custom.repository.impl.CustomImageRepositoryImpl;
import org.tattour.server.domain.custom.repository.impl.CustomRepositoryImpl;
import org.tattour.server.domain.custom.repository.impl.CustomStyleRepositoryImpl;
import org.tattour.server.domain.custom.repository.impl.CustomThemeRepositoryImpl;
import org.tattour.server.domain.custom.service.dto.request.CreateCustomInfo;
import org.tattour.server.domain.custom.service.dto.response.CustomInfo;
import org.tattour.server.domain.style.repository.impl.StyleRepositoryImpl;
import org.tattour.server.domain.theme.repository.impl.ThemeRepositoryImpl;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.repository.impl.UserRepositoryImpl;
import org.tattour.server.infra.s3.S3Service;

@Service
@RequiredArgsConstructor
public class CustomServiceImpl implements CustomService {

	private final S3Service s3Service;
	private final CustomRepositoryImpl customRepository;
	private final CustomImageRepositoryImpl customImageRepository;
	private final CustomThemeRepositoryImpl customThemeRepository;
	private final CustomStyleRepositoryImpl customStyleRepository;
	private final UserRepositoryImpl userRepository;
	private final ThemeRepositoryImpl themeRepository;
	private final StyleRepositoryImpl styleRepository;

	private final String directoryPath = "custom";

	@Override
	@Transactional
	public CustomInfo createCustom(CreateCustomInfo customInfo, Integer userId) {
		User user = userRepository.getReferenceById(userId);
		List<CustomTheme> customThemes = new ArrayList<>();
		List<CustomStyle> customStyles = new ArrayList<>();
		String mainImageUrl = null;
		List<CustomImage> customImages = new ArrayList<>();

		if (!Objects.isNull(customInfo.getThemes())) {
			customThemes = customInfo.getThemes().stream()
				.map(theme -> CustomTheme.of(themeRepository.getOne(theme)))
				.collect(Collectors.toList());
		}
		if (!Objects.isNull(customInfo.getStyles())) {
			customStyles = customInfo.getStyles().stream()
				.map(style -> CustomStyle.of(styleRepository.getOne(style)))
				.collect(Collectors.toList());
		}
		if(!Objects.isNull(customInfo.getMainImage())) {
			mainImageUrl = s3Service.uploadImage(customInfo.getMainImage(), directoryPath);
		}
		if(!Objects.isNull(customInfo.getImages())) {
			customImages = s3Service.uploadImageList(customInfo.getImages(), directoryPath)
				.stream()
				.map(image -> CustomImage.of(image))
				.collect(Collectors.toList());
		}
		Custom custom = Custom.from(user,
			customThemes,
			customStyles,
			mainImageUrl,
			customImages,
			customInfo.getHaveDesign(),
			customInfo.getSize(),
			customInfo.getName(),
			customInfo.getDescription(),
			customInfo.getDemand(),
			customInfo.getCount(),
			customInfo.getIsColored(),
			customInfo.getIsPublic(),
			customInfo.getIsCompleted(),
			customInfo.getViewCount()
		);
		for (CustomTheme customTheme : customThemes) {
			customTheme.setCustom(custom);
		}
		for (CustomStyle customStyle : customStyles) {
			customStyle.setCustom(custom);
		}
		for (CustomImage customImage : customImages) {
			customImage.setCustom(custom);
		}
		customRepository.save(custom);
		return null;
	}
}

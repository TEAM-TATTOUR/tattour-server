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
import org.tattour.server.domain.custom.domain.Process;
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
	public CustomInfo createCustom(CreateCustomInfo CreatecustomInfo, Integer userId) {
		User user = userRepository.getReferenceById(userId);
		List<CustomTheme> customThemes = new ArrayList<>();
		List<CustomStyle> customStyles = new ArrayList<>();
		String mainImageUrl = null;
		List<CustomImage> customImages = new ArrayList<>();
		Process process = null;
		if (!Objects.isNull(CreatecustomInfo.getThemes())) {
			customThemes = CreatecustomInfo.getThemes().stream()
				.map(theme -> CustomTheme.of(themeRepository.getOne(theme)))
				.collect(Collectors.toList());
		}
		if (!Objects.isNull(CreatecustomInfo.getStyles())) {
			customStyles = CreatecustomInfo.getStyles().stream()
				.map(style -> CustomStyle.of(styleRepository.getOne(style)))
				.collect(Collectors.toList());
		}
		if(!Objects.isNull(CreatecustomInfo.getMainImage())) {
			mainImageUrl = s3Service.uploadImage(CreatecustomInfo.getMainImage(), directoryPath);
		}
		if(!Objects.isNull(CreatecustomInfo.getImages())) {
			customImages = s3Service.uploadImageList(CreatecustomInfo.getImages(), directoryPath)
				.stream()
				.map(image -> CustomImage.of(image))
				.collect(Collectors.toList());
		}
		if(!Objects.isNull(CreatecustomInfo.getImages())) {
			process = Process.receiving;
		}
		Custom custom = Custom.from(user,
			customThemes,
			customStyles,
			mainImageUrl,
			customImages,
			CreatecustomInfo.getHaveDesign(),
			CreatecustomInfo.getSize(),
			CreatecustomInfo.getName(),
			CreatecustomInfo.getDescription(),
			CreatecustomInfo.getDemand(),
			CreatecustomInfo.getCount(),
			CreatecustomInfo.getIsColored(),
			CreatecustomInfo.getIsPublic(),
			CreatecustomInfo.getIsCompleted(),
			CreatecustomInfo.getViewCount(),
			process
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
		return CustomInfo.of(custom);
	}
}

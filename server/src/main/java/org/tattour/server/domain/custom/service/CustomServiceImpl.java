package org.tattour.server.domain.custom.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.domain.CustomImage;
import org.tattour.server.domain.custom.domain.CustomSize;
import org.tattour.server.domain.custom.domain.CustomStyle;
import org.tattour.server.domain.custom.domain.CustomTheme;
import org.tattour.server.domain.custom.domain.CustomProcess;
import org.tattour.server.domain.custom.exception.InvalidCustomPriceException;
import org.tattour.server.domain.custom.exception.NotFoundCustomException;
import org.tattour.server.domain.custom.repository.impl.CustomRepositoryImpl;
import org.tattour.server.domain.custom.service.dto.request.GetCustomSummaryInfo;
import org.tattour.server.domain.custom.service.dto.request.UpdateCustomInfo;
import org.tattour.server.domain.custom.service.dto.response.CustomApplySummaryInfoList;
import org.tattour.server.domain.custom.service.dto.response.CustomInfo;
import org.tattour.server.domain.custom.service.dto.response.CustomSummaryList;
import org.tattour.server.domain.style.service.StyleService;
import org.tattour.server.domain.theme.service.ThemeService;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.service.UserService;
import org.tattour.server.global.exception.UnauthorizedException;
import org.tattour.server.global.util.EntityDtoMapper;
import org.tattour.server.infra.discord.service.DiscordMessageService;
import org.tattour.server.infra.s3.S3Service;

@Service
@RequiredArgsConstructor
public class CustomServiceImpl implements CustomService {

	private final CustomRepositoryImpl customRepository;
	private final S3Service s3Service;
	private final ThemeService themeService;
	private final StyleService styleService;
	private final UserService userService;
	private final DiscordMessageService discordMessageService;

	private final String directoryPath = "custom";

	@Value("${image.default.custom}")
	private String defaultImageUrl;

	@Override
	@Transactional(readOnly = true)
	public Custom getCustomById(Integer customId, Integer userId) {
		Custom custom = customRepository.findById(customId)
			.orElseThrow(NotFoundCustomException::new);
		User user = userService.getUserByUserId(userId);
		if (!custom.getUser().equals(user) && !userId.equals(1)) {
			throw new UnauthorizedException();
		}
		return custom;
	}

	@Override
	@Transactional
	public Integer createCustom(Boolean haveDesign, Integer userId) {
		User user = userService.getUserByUserId(userId);
		Custom custom = Custom.from(user, haveDesign, "임시 저장", defaultImageUrl,false, 0);
		customRepository.save(custom);
		return custom.getId();
	}

	@Override
	@Transactional
	public CustomInfo updateCustom(UpdateCustomInfo updateCustomInfo) {
		Custom custom = getCustomById(updateCustomInfo.getCustomId(), updateCustomInfo.getUserId());
		if (!Objects.isNull(updateCustomInfo.getSize())) {
			custom.setSize(CustomSize.getCustomSize(updateCustomInfo.getSize()));
		}
		if (!Objects.isNull(updateCustomInfo.getMainImage())) {
			String mainImageUrl = s3Service.uploadImage(updateCustomInfo.getMainImage(),
				directoryPath);
			custom.setMainImageUrl(mainImageUrl);
		}
		if (!Objects.isNull(updateCustomInfo.getImages())) {
			List<CustomImage> customImages = s3Service.uploadImageList(updateCustomInfo.getImages(),
					directoryPath)
				.stream()
				.map(image -> CustomImage.from(image, custom))
				.collect(Collectors.toList());
			custom.setImages(customImages);
		}
		if (!Objects.isNull(updateCustomInfo.getIsColored())) {
			custom.setColored(updateCustomInfo.getIsColored());
		}
		if (!Objects.isNull(updateCustomInfo.getThemes())) {
			List<CustomTheme> customThemes = updateCustomInfo.getThemes().stream()
				.map(themeId -> CustomTheme.from(custom, themeService.getThemeById(themeId)))
				.collect(Collectors.toList());
			if (!custom.getCustomThemes().contains(customThemes)) {
				custom.setCustomThemes(customThemes);
			}
		}
		if (!Objects.isNull(updateCustomInfo.getStyles())) {
			List<CustomStyle> customStyles = updateCustomInfo.getStyles().stream()
				.map(styleId -> CustomStyle.from(custom, styleService.getStyleById(styleId)))
				.collect(Collectors.toList());
			custom.setCustomStyles(customStyles);
			if (!custom.getCustomStyles().contains(customStyles)) {
				custom.setCustomStyles(customStyles);
			}
		}
		if (!Objects.isNull(updateCustomInfo.getName())) {
			custom.setName(updateCustomInfo.getName());
		}
		if (!Objects.isNull(updateCustomInfo.getDemand())) {
			custom.setDemand(updateCustomInfo.getDemand());
		}
		if (!Objects.isNull(updateCustomInfo.getDescription())) {
			custom.setDescription(updateCustomInfo.getDescription());
		}
		if (!Objects.isNull(updateCustomInfo.getIsPublic())) {
			custom.setPublic(updateCustomInfo.getIsPublic());
		}
		if (!Objects.isNull(updateCustomInfo.getIsCompleted())) {
			if (updateCustomInfo.getIsCompleted()) {
				custom.setCompleted(updateCustomInfo.getIsCompleted());
				custom.setCustomProcess(CustomProcess.RECEIVING);
				discordMessageService.sendCustomApplyMessage(custom);
			}
		}
		if (!Objects.isNull(updateCustomInfo.getCount())) {
			custom.setCount(updateCustomInfo.getCount());
		}
		if (!Objects.isNull(custom.getCount()) && !Objects.isNull(custom.getSize())
			&& !Objects.isNull(custom.getIsPublic()) && !Objects.isNull(
			updateCustomInfo.getPrice())) {
			custom.calPrice();
			if (updateCustomInfo.getPrice().equals(custom.getPrice())) {
				throw new InvalidCustomPriceException();
			}
		}
		if (!Objects.isNull(updateCustomInfo.getViewCount())) {
			custom.setViewCount(updateCustomInfo.getViewCount());
		}
		customRepository.save(custom);
		return CustomInfo.of(custom);
	}

	@Override
	@Transactional(readOnly = true)
	public CustomSummaryList getCustomSummaryCompleteListByUserId(Integer userId) {
		List<Custom> customs = customRepository.findAllByUser_IdAndIsCompletedTrue(userId);
		return CustomSummaryList.of(customs);
	}

	@Override
	@Transactional(readOnly = true)
	public CustomSummaryList getCustomSummaryInCompleteListByUserId(Integer userId) {
		List<Custom> customs = customRepository.findAllByUser_IdAndIsCompletedFalse(userId);
		return CustomSummaryList.of(customs);
	}

	@Override
	public CustomApplySummaryInfoList getCustomApplySummaryInfoList(GetCustomSummaryInfo req) {
		List<Custom> customs = customRepository.findAllByUser_IdAndIsCompletedFalse(req.getUserId());
		return CustomApplySummaryInfoList.of(EntityDtoMapper.INSTANCE.toCustomApplySummaryInfoList(customs));
	}
}

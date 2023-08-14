package org.tattour.server.domain.custom.facade.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.domain.CustomImage;
import org.tattour.server.domain.custom.facade.CustomFacade;
import org.tattour.server.domain.custom.facade.dto.request.UpdateCustomReq;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomRes;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomSummaryListRes;
import org.tattour.server.domain.custom.repository.CustomRepository;
import org.tattour.server.domain.custom.service.CustomImageService;
import org.tattour.server.domain.custom.service.CustomService;
import org.tattour.server.domain.custom.service.CustomStyleService;
import org.tattour.server.domain.custom.service.CustomThemeService;
import org.tattour.server.domain.point.domain.PointLogCategory;
import org.tattour.server.domain.point.domain.UserPointLog;
import org.tattour.server.domain.point.service.PointService;
import org.tattour.server.domain.custom.provider.impl.CustomProviderImpl;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.service.UserService;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.infra.discord.service.DiscordMessageService;
import org.tattour.server.infra.s3.S3Service;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomFacadeImpl implements CustomFacade {

	private final CustomService customService;
	private final CustomImageService customImageService;
	private final CustomThemeService customThemeService;
	private final CustomStyleService customStyleService;
	private final UserService userService;
	private final PointService pointService;
	private final S3Service s3Service;
	private final DiscordMessageService discordMessageService;
	private final CustomProviderImpl customProvider;
	private final CustomRepository customRepository;

	private static final String directoryPath = "custom";
	private static final Integer customPoint = 990;

	@Override
	@Transactional
	public Integer createCustom(Boolean haveDesign, Integer userId) {
		User user = userService.readUserById(userId);
		if (user.isLackOfPoint(customPoint)) {
			throw new BusinessException(ErrorType.LACK_OF_POINT_EXCEPTION);
		}
		userService.updateUserPoint(user, -customPoint);
		pointService.savePointLog(
				UserPointLog.of(
						PointLogCategory.APPLY_CUSTOM,
						"커스텀 스티커 신청",
						customPoint,
						user.getPoint(),
						user));
		Custom custom = customService.createInitCustom(user, haveDesign);
		customService.save(custom);
		return custom.getId();
	}

	@Override
	public ReadCustomRes readCustomById(Integer customId, Integer userId) {
		Custom custom = customProvider.getCustomById(customId, userId);
		return ReadCustomRes.from(custom);
	}

	@Override
	@Transactional(readOnly = true)
	public ReadCustomSummaryListRes getCustomSummaryCompleteListByUserId(Integer userId) {
		List<Custom> customs = customRepository.findAllByUser_IdAndIsCompletedTrue(userId);
		return ReadCustomSummaryListRes.from(customs);
	}

	@Override
	@Transactional(readOnly = true)
	public ReadCustomSummaryListRes getCustomSummaryInCompleteListByUserId(Integer userId) {
		List<Custom> customs = customRepository.findAllByUser_IdAndIsCompletedFalse(userId);
		return ReadCustomSummaryListRes.from(customs);
	}

	@Override
	public ReadCustomSummaryListRes readCustomSummaryInfoAfterDateByUserId(int userId, String date) {
		List<Custom> customs = customRepository.findAllByUser_IdAndCreatedAtAfter(userId, date);
		return ReadCustomSummaryListRes.from(customs);
	}

	@Override
	@Transactional
	public ReadCustomRes updateCustom(UpdateCustomReq updateCustomReq) {
		Custom custom = customProvider.getCustomById(
				updateCustomReq.getCustomId(),
				updateCustomReq.getUserId());
		Custom updateCustom = updateCustomReq.newCustom();

		// 손그림 등록
		if (!Objects.isNull(updateCustomReq.getHandDrawingImage())) {
			customService.setHandDrawingImage(
					updateCustom,
					updateCustomReq.getHandDrawingImage());
		}

		// 이미지 리스트의 첫번째가 메인 이미지임
		if (updateCustomReq.getImages().size() > 0) {
			customService.setMainImageUrl(
					updateCustom,
					updateCustomReq.getImages().get(0));
			updateCustomReq.getImages().remove(0);
		}
		customService.updateCustom(custom, updateCustom);

		// 이미지들 등록
		if (!Objects.isNull(updateCustomReq.getImages())) {
			List<CustomImage> customImages =
					s3Service.uploadImageList(
									updateCustomReq.getImages(),
									directoryPath)
							.stream()
							.map(image -> CustomImage.of(image, custom))
							.collect(Collectors.toList());
			customImageService.saveAll(customImages);
		}

		// 테마 등록
		if (!Objects.isNull(updateCustomReq.getThemes())) {
			customThemeService.saveAllByCustomAndThemeIdList(
					custom,
					updateCustomReq.getThemes());
		}

		// 스타일 등록
		if (!Objects.isNull(updateCustomReq.getStyles())) {
			customStyleService.saveByCustomAndStyleIdList(
					custom,
					updateCustomReq.getStyles());
		}
		if( custom.getIsCompleted()) {
			discordMessageService.sendCustomApplyMessage(custom);
			userService.updateUserPoint(custom.getUser(), custom.getPrice());
		}
		return ReadCustomRes.from(custom);
	}

	@Override
	@Transactional
	public ReadCustomRes updateCustomProcess(UpdateCustomReq updateCustomReq) {
		Custom custom =
				customProvider.getCustomById(
						updateCustomReq.getCustomId(),
						updateCustomReq.getUserId());
		customService.updateCustomProcess(custom, updateCustomReq.getCustomProcess());
		return ReadCustomRes.from(custom);
	}
}

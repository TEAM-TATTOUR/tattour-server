package org.tattour.server.domain.custom.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.domain.CustomProcess;
import org.tattour.server.domain.custom.exception.InvalidCustomCompletedException;
import org.tattour.server.domain.custom.exception.InvalidCustomPriceException;
import org.tattour.server.domain.custom.provider.CustomProvider;
import org.tattour.server.domain.custom.repository.CustomRepository;
import org.tattour.server.domain.custom.service.CustomService;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.infra.discord.service.DiscordMessageService;
import org.tattour.server.infra.s3.S3Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomServiceImpl implements CustomService {

	private final CustomRepository customRepository;
	private final CustomProvider customProvider;
	private final DiscordMessageService discordMessageService;
	private final S3Service s3Service;


	@Value("${image.default.custom}")
	private String defaultImageUrl;
	private static final String directoryPath = "custom";

	@Override
	public Custom save(Custom custom) {
		return customRepository.save(custom);
	}

	@Override
	public Custom createInitCustom(User user, Boolean haveDesign) {
		return Custom.of(
				user,
				haveDesign,
				"임시 저장",
				defaultImageUrl,
				false,
				0);
	}

//	@Override
//	public Custom updateCustom(Custom updateCustom) {
//		if (updateCustom.getIsCompleted()) {
//			if (!isValidatePrice(updateCustom)) {
//				throw new InvalidCustomPriceException();
//			}
//			updateCustom.setCustomProcess(CustomProcess.RECEIVING);
//			discordMessageService.sendCustomApplyMessage(updateCustom);
//		}
//		return customRepository.save(updateCustom);
//	}

	@Override
	public Custom updateCustom(Custom custom, Custom updateCustomInfo) {
		if(Objects.isNull(updateCustomInfo.getMainImageUrl())) {
			custom.setMainImageUrl(updateCustomInfo.getMainImageUrl());
		}
		if(Objects.isNull(updateCustomInfo.getHandDrawingImageUrl())) {
			custom.setHandDrawingImageUrl(updateCustomInfo.getHandDrawingImageUrl());
		}
		if (!Objects.isNull(updateCustomInfo.getSize())) {
			custom.setSize(updateCustomInfo.getSize());
		}
		if (!Objects.isNull(updateCustomInfo.getName())) {
			custom.setName(updateCustomInfo.getName());
		}
		if (!Objects.isNull(updateCustomInfo.getDescription())) {
			custom.setDescription(updateCustomInfo.getDescription());
		}
		if (!Objects.isNull(updateCustomInfo.getDemand())) {
			custom.setDemand(updateCustomInfo.getDemand());
		}
		if (!Objects.isNull(updateCustomInfo.getCount())) {
			custom.setCount(updateCustomInfo.getCount());
		}
		if (!Objects.isNull(updateCustomInfo.getIsColored())) {
			custom.setColored(updateCustomInfo.getIsColored());
		}
		if (!Objects.isNull(updateCustomInfo.getIsPublic())) {
			custom.setPublic(updateCustomInfo.getIsPublic());
		}
		if (!Objects.isNull(updateCustomInfo.getViewCount())) {
			custom.setViewCount(updateCustomInfo.getViewCount());
		}
		if (!Objects.isNull(updateCustomInfo.getIsCompleted())) {
			if (updateCustomInfo.getIsCompleted()) {
				if (!custom.calPrice().equals(updateCustomInfo.getPrice())) {
					throw new InvalidCustomPriceException();
				}
				custom.setPrice(updateCustomInfo.getPrice());
				custom.setCompleted(updateCustomInfo.getIsCompleted());
				custom.setCustomProcess(CustomProcess.RECEIVING);
			}
		}
		return customRepository.save(custom);
	}

	@Override
	public Custom updateCustomProcess(Custom custom, CustomProcess customProcess) {
		if(Objects.isNull(custom.getIsCompleted())) {
			throw new InvalidCustomCompletedException();
		}
		if (!custom.getIsCompleted()) {
			throw new InvalidCustomCompletedException();
		}
		custom.setCustomProcess(customProcess);
		return custom;
	}

	@Override
	public void setHandDrawingImage(Custom updateCustom, MultipartFile handDrawingImage) {
		updateCustom.setHandDrawingImageUrl(
				s3Service.uploadImage(
						handDrawingImage,
						directoryPath));
	}

	@Override
	public void setMainImageUrl(Custom updateCustom, MultipartFile mainImage) {
		updateCustom.setMainImageUrl(
				s3Service.uploadImage(
						mainImage,
						directoryPath));
	}
}

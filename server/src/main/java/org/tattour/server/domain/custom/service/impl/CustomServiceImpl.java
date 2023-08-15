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

	@Override
	public Custom updateCustom(Custom custom, Custom updateCustom) {
		if(!Objects.isNull(updateCustom.getMainImageUrl())) {
			custom.setMainImageUrl(updateCustom.getMainImageUrl());
		}
		if(!Objects.isNull(updateCustom.getHandDrawingImageUrl())) {
			custom.setHandDrawingImageUrl(updateCustom.getHandDrawingImageUrl());
		}
		if (!Objects.isNull(updateCustom.getSize())) {
			custom.setSize(updateCustom.getSize());
		}
		if (!Objects.isNull(updateCustom.getName())) {
			custom.setName(updateCustom.getName());
		}
		if (!Objects.isNull(updateCustom.getDescription())) {
			custom.setDescription(updateCustom.getDescription());
		}
		if (!Objects.isNull(updateCustom.getDemand())) {
			custom.setDemand(updateCustom.getDemand());
		}
		if (!Objects.isNull(updateCustom.getCount())) {
			custom.setCount(updateCustom.getCount());
		}
		if (!Objects.isNull(updateCustom.getIsColored())) {
			custom.setColored(updateCustom.getIsColored());
		}
		if (!Objects.isNull(updateCustom.getIsPublic())) {
			custom.setPublic(updateCustom.getIsPublic());
		}
		if (!Objects.isNull(updateCustom.getViewCount())) {
			custom.setViewCount(updateCustom.getViewCount());
		}
		if (!Objects.isNull(updateCustom.getIsCompleted())) {
			if (updateCustom.getIsCompleted()) {
				if (!custom.calPrice().equals(updateCustom.getPrice())) {
					throw new InvalidCustomPriceException();
				}
				custom.setPrice(updateCustom.getPrice());
				custom.setCompleted(updateCustom.getIsCompleted());
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

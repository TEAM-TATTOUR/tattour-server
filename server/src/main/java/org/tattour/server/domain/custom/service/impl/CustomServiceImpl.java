package org.tattour.server.domain.custom.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.domain.CustomProcess;
import org.tattour.server.domain.custom.domain.CustomSize;
import org.tattour.server.domain.custom.exception.InvalidCustomCompletedException;
import org.tattour.server.domain.custom.exception.InvalidCustomPriceException;
import org.tattour.server.domain.custom.provider.CustomProvider;
import org.tattour.server.domain.custom.repository.CustomRepository;
import org.tattour.server.domain.custom.service.CustomService;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.infra.discord.service.DiscordMessageService;
import org.tattour.server.infra.s3.S3Service;

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
	public Custom updateCustom(Custom updateCustom) {
		if (updateCustom.getIsCompleted()) {
			if (!isValidatePrice(updateCustom)) {
				throw new InvalidCustomPriceException();
			}
			updateCustom.setCustomProcess(CustomProcess.RECEIVING);
			discordMessageService.sendCustomApplyMessage(updateCustom);
		}
		return customRepository.save(updateCustom);
	}

	@Override
	public Custom updateCustomProcess(Custom custom, CustomProcess customProcess) {
		if (!custom.getIsCompleted()) {
			throw new InvalidCustomCompletedException();
		}
		custom.setCustomProcess(customProcess);
		return custom;
	}

	private boolean isValidatePrice(Custom custom) {
		custom.setSize(getCustomSize(custom));
		return custom.calPrice().equals(custom.getPrice());
	}

	// custom size 반환하는 메소드
	private CustomSize getCustomSize(Custom custom) {
		if (Objects.isNull(custom.getSize())) {
			return customProvider.getCustomById(
							custom.getId(),
							custom.getUser().getId())
					.getSize();
		}
		return custom.getSize();
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

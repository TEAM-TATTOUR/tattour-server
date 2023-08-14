package org.tattour.server.domain.sticker.facade.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.domain.StickerImage;
import org.tattour.server.domain.sticker.domain.StickerSort;
import org.tattour.server.domain.sticker.domain.StickerStyle;
import org.tattour.server.domain.sticker.domain.StickerTheme;
import org.tattour.server.domain.sticker.facade.StickerFacade;
import org.tattour.server.domain.sticker.facade.dto.response.ReadOrderSheetStickerRes;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.sticker.facade.dto.request.CreateStickerReq;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerForUserRes;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerSummaryListRes;
import org.tattour.server.domain.sticker.service.StickerImageService;
import org.tattour.server.domain.sticker.service.StickerService;
import org.tattour.server.domain.sticker.service.StickerStyleService;
import org.tattour.server.domain.sticker.service.StickerThemeService;
import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.style.provider.StyleProvider;
import org.tattour.server.domain.theme.domain.Theme;
import org.tattour.server.domain.theme.provider.ThemeProvider;
import org.tattour.server.domain.user.service.ProductLikedService;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.infra.s3.S3Service;

@Service
@RequiredArgsConstructor
public class StickerFacadeImpl implements StickerFacade {

	private final StickerService stickerService;
	private final StickerImageService stickerImageService;
	private final StickerThemeService stickerThemeService;
	private final StickerStyleService stickerStyleService;
	private final ProductLikedService productLikedService;
	private final StickerProvider stickerProvider;
	private final ThemeProvider themeProvider;
	private final StyleProvider styleProvider;
	private final JwtService jwtService;
	private final S3Service s3Service;

	private static final String directoryPath = "sticker";

	@Override
	@Transactional(readOnly = true)
	public ReadStickerForUserRes readStickerForUser(Integer stickerId, String authorization) {
		Boolean productLiked = getProductLiked(stickerId, authorization);
		Sticker sticker = stickerProvider.getById(stickerId);
		return ReadStickerForUserRes.of(sticker, productLiked);
	}

	// Todo : 위치 옮기기
	private Boolean getProductLiked(Integer stickerId, String authorization) {
		Integer userId = getUserId(authorization);
		if (Objects.isNull(userId)) {
			return false;
		}
		return productLikedService.checkProductLikedExists(userId, stickerId);
	}

	// Todo : 위치 옮기기
	private Integer getUserId(String authorization) {
		if (Objects.isNull(authorization)) {
			return null;
		}
		if (!StringUtils.hasText(authorization) || !authorization.startsWith("Bearer ")) {
			throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
		}
		String token = authorization.substring("Bearer ".length());
		if (!jwtService.verifyToken(token)) {
			throw new BusinessException(ErrorType.INVALID_JWT_TOKEN_EXCEPTION);
		}
		String tokenContents = jwtService.getJwtContents(token);
		return Integer.parseInt(tokenContents);
	}

	@Override
	@Transactional(readOnly = true)
	public ReadStickerSummaryListRes readStickerSummaryList() {
		List<Sticker> stickers = stickerProvider.getAllByStateTrue();
		return ReadStickerSummaryListRes.from(stickers);
	}

	@Override
	@Transactional
	public Integer createSticker(CreateStickerReq request) {
		String mainImageUrl = s3Service.uploadImage(request.getMainImage(), directoryPath);
		Sticker sticker = Sticker.of(
				request.getName(),
				request.getDescription(),
				mainImageUrl,
				request.getIsCustom(),
				request.getPrice(),
				request.getComposition(),
				request.getSize(),
				request.getShippingFee(),
				true);
		stickerService.save(sticker);
		request.getImages()
				.stream()
				.map(image -> s3Service.uploadImage(image, directoryPath))
				.map(imageUrl -> StickerImage.of(sticker, imageUrl))
				.forEach(stickerImageService::save);
		/*
		// Todo: 테스트 해보기
		if (!Objects.isNull(request.getImages())) {
			List<String> imageUrls = s3Service.uploadImageList(request.getImages(), directoryPath);
			imageUrls
				.stream()
				.map(imageUrl -> StickerImage.from(sticker, imageUrl))
				.forEach(stickerImageService::save);
		}
		 */
		request
				.getThemes()
				.stream()
				.map(themeId -> StickerTheme.of(sticker, themeProvider.getById(themeId)))
				.forEach(stickerThemeService::save);
		request.getStyles().stream()
				.map(styleId -> StickerStyle.of(sticker, styleProvider.getById(styleId)))
				.forEach(stickerStyleService::save);
		return sticker.getId();
	}

	@Override
	@Transactional(readOnly = true)
	public ReadStickerSummaryListRes readHotCustomStickerList() {
		List<Sticker> stickers = stickerProvider.getAllByIsCustomTrueAndStateTrue();
		return ReadStickerSummaryListRes.from(stickers);
	}

	// Todo : querydsl로 변경하기
	@Override
	@Transactional(readOnly = true)
	public ReadStickerSummaryListRes readSimilarStickerSummaryList(Integer stickerId) {
		List<Sticker> result = new ArrayList<>();
		Sticker sticker = stickerProvider.getById(stickerId);
		List<Theme> themes = sticker.getStickerThemes().stream()
				.map(stickerTheme -> stickerTheme.getTheme())
				.collect(Collectors.toList());
		List<Style> styles = sticker.getStickerStyles().stream()
				.map(stickerStyle -> stickerStyle.getStyle())
				.collect(Collectors.toList());
		stickerService.addStickerListByThemeList(result, themes);
		stickerService.addStickerListByStyleList(result, styles);
		result.remove(sticker);
		return ReadStickerSummaryListRes.from(result);
	}

	@Override
	@Transactional(readOnly = true)
	public ReadStickerSummaryListRes readFilterStickerSummaryList(
			String sort,
			String theme,
			String style) {
		List<Sticker> result = new ArrayList<>();
		StickerSort stickerSort = StickerSort.getStickerSort(sort);
		if (theme.isEmpty() && style.isEmpty()) {
			result = stickerProvider.getAll();
			sortStickerListByStickerSort(result, stickerSort);
			return ReadStickerSummaryListRes.from(result);
		}
		if (!style.isEmpty()) {
			Theme fileterTheme = themeProvider.getByName(theme);
			stickerService.addStickerListByTheme(result, fileterTheme);
			sortStickerListByStickerSort(result, stickerSort);
			return ReadStickerSummaryListRes.from(result);
		}
		if (!theme.isEmpty()) {
			Style filterStyle = styleProvider.getByName(style);
			stickerService.addStickerListByStyle(result, filterStyle);
			sortStickerListByStickerSort(result, stickerSort);
			return ReadStickerSummaryListRes.from(result);
		}
		Theme fileterTheme = themeProvider.getByName(theme);
		Style filterStyle = styleProvider.getByName(style);
		stickerService.addStickerListByTheme(result, fileterTheme);
		stickerService.addStickerListByStyle(result, filterStyle);
		sortStickerListByStickerSort(result, stickerSort);
		return ReadStickerSummaryListRes.from(result);
	}

	// Todo : provider 코드 대체하기
	@Override
	public ReadOrderSheetStickerRes readOrderSheetSticker(Integer stickerId) {
		Sticker sticker = stickerProvider.getById(stickerId);
		sticker.getDiscountPrice();
		return ReadOrderSheetStickerRes.of(
				sticker.getMainImageUrl(),
				sticker.getName(),
				sticker.getPrice(),
				sticker.getDiscountPrice());
	}

	private void sortStickerListByStickerSort(List<Sticker> stickers, StickerSort sort) {
		switch (sort) {
			case POPULARITY:
				break;
			case PRICE_HIGH:
				Collections.sort(stickers, (o1, o2) -> o2.getPrice() - o1.getPrice());
				break;
			case PRICE_LOW:
				Collections.sort(stickers, (o1, o2) -> o1.getPrice() - o2.getPrice());
				break;
		}
	}
}

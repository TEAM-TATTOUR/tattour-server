package org.tattour.server.domain.sticker.facade.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.domain.StickerImage;
import org.tattour.server.domain.sticker.domain.StickerSort;
import org.tattour.server.domain.sticker.domain.StickerStyle;
import org.tattour.server.domain.sticker.domain.StickerTheme;
import org.tattour.server.domain.sticker.exception.NotFoundStickerSortException;
import org.tattour.server.domain.sticker.facade.StickerFacade;
import org.tattour.server.domain.sticker.facade.dto.request.CreateStickerReq;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerForUserRes;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerSummaryListRes;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.sticker.service.StickerImageService;
import org.tattour.server.domain.sticker.service.StickerService;
import org.tattour.server.domain.sticker.service.StickerStyleService;
import org.tattour.server.domain.sticker.service.StickerThemeService;
import org.tattour.server.domain.style.provider.StyleProvider;
import org.tattour.server.domain.theme.provider.ThemeProvider;
import org.tattour.server.domain.user.service.ProductLikedService;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.infra.s3.S3Service;

@Slf4j
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
        Sticker sticker = stickerProvider.getById(stickerId);
        if (Objects.isNull(authorization)) {
            return ReadStickerForUserRes.of(sticker, false);
        }
        // Todo : authorization을 통해 userId 가져오기 -> 메서드 분리하기
        Integer userId = getUserId(authorization);
        Boolean productLiked = productLikedService.checkProductLikedExists(userId, stickerId);
        return ReadStickerForUserRes.of(sticker, productLiked);
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
        String tokenContents = jwtService.getJwtContents(token).getUserId();
        return Integer.parseInt(tokenContents);
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
        List<Sticker> stickers = stickerProvider.getAllCustomStickerOrderByOrder();
        return ReadStickerSummaryListRes.from(stickers);
    }

    @Override
    @Transactional(readOnly = true)
    public ReadStickerSummaryListRes readSimilarStickerSummaryList(Integer stickerId) {
        List<Sticker> stickers =
                stickerProvider.getAllSameThemeOrStyleById(stickerId);
        stickers.removeIf(sticker -> sticker.getId().equals(stickerId));
        return ReadStickerSummaryListRes.from(stickers);
    }

    @Override
    @Transactional(readOnly = true)
    public ReadStickerSummaryListRes readFilterStickerSummaryList(
            String sort,
            String theme,
            String style) {
        StickerSort stickerSort = StickerSort.getStickerSort(sort);
        List<Sticker> result = new ArrayList<>();
        switch (stickerSort) {
            case POPULARITY:
                result = stickerProvider
                        .getAllByThemeAndStyleOrderByOrder(theme, style);
                stickerService.sortStickerListByNumberOfOrderDesc(result);
                return ReadStickerSummaryListRes.from(result);
            case PRICE_HIGH:
                result = stickerProvider
                        .getAllByThemeAndStyleOrderByPriceDesc(theme, style);
                return ReadStickerSummaryListRes.from(result);
            case PRICE_LOW:
                result = stickerProvider
                        .getAllByThemeAndStyleOrderByPrice(theme, style);
                return ReadStickerSummaryListRes.from(result);
            default:
                throw new NotFoundStickerSortException();
        }
    }
}

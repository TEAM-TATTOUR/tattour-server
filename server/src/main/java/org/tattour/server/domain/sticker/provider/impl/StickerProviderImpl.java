package org.tattour.server.domain.sticker.provider.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.sticker.provider.dto.response.ReadOrderSheetStickerInfo;
import org.tattour.server.domain.sticker.repository.impl.StickerRepositoryImpl;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@Service
@RequiredArgsConstructor
public class StickerProviderImpl implements StickerProvider {

    private final StickerRepositoryImpl stickerRepository;

    @Override
    public Sticker getStickerById(Integer stickerId) {
        return stickerRepository.findById(stickerId)
                .orElseThrow(() -> new BusinessException(ErrorType.NOT_FOUND_STICKER_EXCEPTION));
    }

    @Override
    public ReadOrderSheetStickerInfo readOrderSheetStickerInfo(Integer stickerId) {
        Sticker sticker = getStickerById(stickerId);
        Integer discountedPrice = null;

        // 할인률이 null이 아닐 때
        if (!Objects.isNull(sticker.getDiscount())) {
            discountedPrice =
                    (sticker.getPrice() * (100 - sticker.getDiscount().getDiscountRate())) / 100;
        }

        return ReadOrderSheetStickerInfo.of(
                sticker.getMainImageUrl(),
                sticker.getName(),
                sticker.getPrice(),
                discountedPrice);
    }
}

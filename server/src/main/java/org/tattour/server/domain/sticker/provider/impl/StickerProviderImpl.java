package org.tattour.server.domain.sticker.provider.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.cart.domain.Cart;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.exception.NotFoundStickerException;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.sticker.provider.vo.StickerOrderInfo;
import org.tattour.server.domain.sticker.repository.StickerRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class StickerProviderImpl implements StickerProvider {

    private final StickerRepository stickerRepository;

    @Override
    public Sticker getById(Integer id) {
        return stickerRepository.findById(id)
                .orElseThrow(NotFoundStickerException::new);
    }

    @Override
    public StickerOrderInfo getStickerOrderInfoFromOrder(int stickerId, int count) {
        return StickerOrderInfo.of(Map.of(getById(stickerId), count));
    }

    @Override
    public StickerOrderInfo getStickerOrderInfoFromCart(List<Cart> carts) {
        return carts.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toMap(
                                Cart::getSticker,
                                Cart::getCount),
                        StickerOrderInfo::of));
    }

    @Override
    public List<Sticker> getAllCustomStickerOrderByOrder() {
        return stickerRepository
                .findAllByStateAndIsCustomInOrderOrder();
    }

    @Override
    public List<Sticker> getAllByThemeAndStyleOrderByOrder(String themeName, String styleName) {
        return stickerRepository
                .findAllByThemeNameAndStyleNameAndStateInOrderOrder(themeName, styleName);
    }

    @Override
    public List<Sticker> getAllByThemeAndStyleOrderByPrice(String themeName, String styleName) {
        return stickerRepository
                .findAllByThemeNameAndStyleNameAndStateInOrderPrice(themeName, styleName);
    }

    @Override
    public List<Sticker> getAllByThemeAndStyleOrderByPriceDesc(String themeName, String styleName) {
        return stickerRepository
                .findAllByThemeNameAndStyleNameAndStateInOrderPriceDesc(themeName, styleName);
    }

    @Override
    public List<Sticker> getAllSameThemeOrStyleById(Integer id) {
        return stickerRepository
                .findAllSameThemeOrStyleById(id);
    }

    @Override
    public List<Sticker> getAllByThemeOrStyleOrNameLike(String word) {
        if (Objects.isNull(word)) {
            return null;
        }
        return stickerRepository
                .findAllByThemeNameOrStyleNameOrNameContaining(word);
    }
}

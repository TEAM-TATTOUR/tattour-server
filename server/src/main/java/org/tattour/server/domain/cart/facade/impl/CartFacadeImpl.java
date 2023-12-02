package org.tattour.server.domain.cart.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.cart.controller.dto.request.SaveCartReq;
import org.tattour.server.domain.cart.controller.dto.response.CartItemsRes;
import org.tattour.server.domain.cart.facade.CartFacade;
import org.tattour.server.domain.cart.service.CartService;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.service.UserService;
import org.tattour.server.global.util.EntityDtoMapper;

@Service
@RequiredArgsConstructor
public class CartFacadeImpl implements CartFacade {
    private final CartService cartService;
    private final UserService userService;
    private final StickerProvider stickerProvider;

    @Override
    @Transactional
    public void saveCartItem(int userId, SaveCartReq req) {
        User user = userService.readUserById(userId);
        Sticker sticker = stickerProvider.getById(req.getStickerId());

        cartService.mergeOrAddToCart(user, sticker, req.getCount());
    }

    @Override
    public CartItemsRes getUserCartItems(int userId) {
        User user = userService.readUserById(userId);

        return CartItemsRes.of(
                EntityDtoMapper.INSTANCE
                        .toCartItemsRes(cartService.findByUser(user)));
    }
}

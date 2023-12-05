package org.tattour.server.domain.cart.facade.impl;

import static org.tattour.server.domain.order.facade.impl.OrderFacadeImpl.SHIPPING_FEE;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.cart.controller.dto.request.CartItemReq;
import org.tattour.server.domain.cart.controller.dto.request.UpdateCartCountReq;
import org.tattour.server.domain.cart.controller.dto.response.CartItemsRes;
import org.tattour.server.domain.cart.domain.Cart;
import org.tattour.server.domain.cart.facade.CartFacade;
import org.tattour.server.domain.cart.service.CartService;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.sticker.provider.vo.StickerOrderInfo;
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
    public void saveCartItem(int userId, CartItemReq req) {
        User user = userService.readUserById(userId);
        Sticker sticker = stickerProvider.getById(req.getStickerId());

        cartService.mergeOrAddToCart(user, sticker, req.getCount());
    }

    //todo : 배송 지역별 배송비 책정
    @Override
    @Transactional(readOnly = true)
    public CartItemsRes getUserCartItems(int userId) {
        List<Cart> carts = cartService.findByUserId(userId);
        StickerOrderInfo stickerOrderInfo = stickerProvider.getStickerOrderInfoFromCart(carts);

        return CartItemsRes.of(
                EntityDtoMapper.INSTANCE.toCartItemsRes(carts),
                EntityDtoMapper.INSTANCE.toOrderAmountRes(stickerOrderInfo, SHIPPING_FEE));
    }

    @Override
    @Transactional
    public void updateCartsCount(int userId, UpdateCartCountReq updateCartCountReq) {
        updateCartCountReq.getCartCountReqs()
                .forEach(req -> cartService
                        .findByIdAndUserId(req.getCartId(), userId)
                        .updateCount(req.getCount()));
    }

    @Override
    @Transactional
    public void deleteCartItem(int userId, int cartId) {
        Cart cart = cartService.findByIdAndUserId(cartId, userId);
        cartService.delete(cart);
    }
}
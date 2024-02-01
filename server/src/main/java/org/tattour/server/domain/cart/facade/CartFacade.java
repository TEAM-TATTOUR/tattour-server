package org.tattour.server.domain.cart.facade;

import org.tattour.server.domain.cart.controller.dto.request.CartItemReq;
import org.tattour.server.domain.cart.controller.dto.request.UpdateCartCountReq;
import org.tattour.server.domain.cart.controller.dto.response.CartItemsRes;

public interface CartFacade {
    void saveCartItem(int userId, CartItemReq req);

    CartItemsRes getUserCartItems(int userId);

    void updateCartsCount(int userId, UpdateCartCountReq req);

    void deleteCartItem(int userId, int cartId);
}

package org.tattour.server.domain.cart.facade;

import org.tattour.server.domain.cart.controller.dto.request.SaveCartReq;
import org.tattour.server.domain.cart.controller.dto.response.CartItemsRes;

public interface CartFacade {
    void saveCartItem(int userId, SaveCartReq req);

    CartItemsRes getUserCartItems(int userId);
}

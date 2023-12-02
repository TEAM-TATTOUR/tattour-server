package org.tattour.server.domain.cart.facade;

import org.tattour.server.domain.cart.controller.dto.request.SaveCartReq;

public interface CartFacade {
    void saveCartItem(int userId, SaveCartReq req);
}

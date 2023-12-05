package org.tattour.server.domain.cart.controller.dto.request;

import lombok.Getter;

@Getter
public class CartCountReq {
    private int cartId;
    private int count;
}

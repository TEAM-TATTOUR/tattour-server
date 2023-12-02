package org.tattour.server.domain.cart.controller.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemsRes {
    private List<CartItemRes> cartItemsRes;

    public static CartItemsRes of(List<CartItemRes> cartItemsRes) {
        return new CartItemsRes(cartItemsRes);
    }
}

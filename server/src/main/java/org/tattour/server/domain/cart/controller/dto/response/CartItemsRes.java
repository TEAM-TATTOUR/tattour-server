package org.tattour.server.domain.cart.controller.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.tattour.server.domain.order.provider.vo.OrderAmountDetailRes;

@Getter
@Setter
@AllArgsConstructor
public class CartItemsRes {
    private List<CartItemRes> cartItemsRes;

    private OrderAmountDetailRes orderAmountDetailRes;

    public static CartItemsRes of(List<CartItemRes> cartItemsRes, OrderAmountDetailRes orderAmountDetailRes) {
        return new CartItemsRes(cartItemsRes, orderAmountDetailRes);
    }
}

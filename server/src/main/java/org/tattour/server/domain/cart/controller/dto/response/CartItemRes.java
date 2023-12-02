package org.tattour.server.domain.cart.controller.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRes {
    private int stickerId;
    private String mainImageUrl;
    private String name;
    private int price;
    private int discountPrice;
    private int count;
}

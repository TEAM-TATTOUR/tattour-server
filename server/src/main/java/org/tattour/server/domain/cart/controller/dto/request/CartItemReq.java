package org.tattour.server.domain.cart.controller.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemReq {
    @NotNull
    private int stickerId;

    @NotNull
    @Min(1)
    private int count;
}

package org.tattour.server.domain.cart.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartItemReq {
    @Schema(description = "스티커 Id")
    @NotNull
    private int stickerId;

    @Schema(description = "스티커 수량")
    @NotNull
    @Min(1)
    private int count;
}

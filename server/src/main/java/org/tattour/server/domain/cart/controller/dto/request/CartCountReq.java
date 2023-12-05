package org.tattour.server.domain.cart.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Schema(description = "장바구니 수량 수정 Request")
@Getter
public class CartCountReq {
    @Schema(description = "장바구니 Id")
    @NotNull(message = "장바구니 id는 null일 수 없습니다.")
    private int cartId;

    @Schema(description = "장바구니 수량")
    @NotNull(message = "장바구니 수량 null일 수 없습니다.")
    private int count;
}

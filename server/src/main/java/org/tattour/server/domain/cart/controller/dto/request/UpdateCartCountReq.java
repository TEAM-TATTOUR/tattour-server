package org.tattour.server.domain.cart.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;

@Schema(description = "장바구니 수량 일괄 수정 Request")
@Getter
public class UpdateCartCountReq {
    @Schema(description = "장바구니 수량 수정 Request 목록")
    private List<CartCountReq> cartCountReqs;
}

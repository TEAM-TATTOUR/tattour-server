package org.tattour.server.domain.cart.controller.dto.request;

import java.util.List;
import lombok.Getter;

@Getter
public class UpdateCartCountReq {
    private List<CartCountReq> cartCountReqs;
}

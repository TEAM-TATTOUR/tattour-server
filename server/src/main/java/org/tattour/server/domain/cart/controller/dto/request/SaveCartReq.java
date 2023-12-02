package org.tattour.server.domain.cart.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SaveCartReq {
    private int count;
    private int stickerId;
}

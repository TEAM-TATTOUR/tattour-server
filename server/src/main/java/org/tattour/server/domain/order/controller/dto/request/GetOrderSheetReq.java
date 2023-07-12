package org.tattour.server.domain.order.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetOrderSheetReq {
    private Integer userId;
    private Integer stickerId;
    private Integer count;
    private Integer shippingFee;
}

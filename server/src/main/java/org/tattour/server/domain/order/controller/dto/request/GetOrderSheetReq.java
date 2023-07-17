package org.tattour.server.domain.order.controller.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetOrderSheetReq {

    @NotNull(message = "stickerId is null")
    private Integer stickerId;

    @NotNull(message = "count is null")
    private Integer count;

    @NotNull(message = "shippingFee is null")
    private Integer shippingFee;
}

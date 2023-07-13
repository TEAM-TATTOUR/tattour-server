package org.tattour.server.domain.order.provider.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetOrderAmountRes {
    private Integer totalAmount;
    private Integer productAmount;
    private Integer shippingFee;

    public static GetOrderAmountRes of(Integer totalAmount, Integer productAmount, Integer shippingFee){
        return new GetOrderAmountRes(totalAmount, productAmount, shippingFee);
    }
}

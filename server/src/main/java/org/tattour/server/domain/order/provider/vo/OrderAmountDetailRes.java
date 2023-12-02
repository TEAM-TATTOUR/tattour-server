package org.tattour.server.domain.order.provider.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "결제 금액 정보")
@Getter
@AllArgsConstructor
public class OrderAmountDetailRes {

    @Schema(description = "총 결제금액", example = "12000")
    private Integer totalAmount;

    @Schema(description = "상품금액", example = "9000")
    private Integer productAmount;

    @Schema(description = "배송비", example = "3000")
    private Integer shippingFee;

    public static OrderAmountDetailRes of(Integer totalAmount, Integer productAmount, Integer shippingFee) {
        return new OrderAmountDetailRes(totalAmount, productAmount, shippingFee);
    }
}

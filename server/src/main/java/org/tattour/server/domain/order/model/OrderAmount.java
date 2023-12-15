package org.tattour.server.domain.order.model;

import lombok.Getter;
import org.tattour.server.domain.sticker.provider.vo.StickerOrderInfo;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@Getter
public class OrderAmount {

    private final int productAmount;
    private final int shippingFee;
    private final int totalAmount;

    private OrderAmount(StickerOrderInfo stickerOrderInfo, int requestTotalAmount,
            int shippingFee) {
        int totalAmount = stickerOrderInfo.calculateTotalAmount(shippingFee);
        validate(requestTotalAmount, totalAmount);

        this.productAmount = stickerOrderInfo.calculateProductAmount();
        this.shippingFee = shippingFee;
        this.totalAmount = totalAmount;
    }

    public static OrderAmount calculate(StickerOrderInfo stickerOrderInfo, int requestTotalAmount,
            int shippingFee) {
        return new OrderAmount(stickerOrderInfo, requestTotalAmount, shippingFee);
    }

    private void validate(int requestTotalAmount, int totalAmount) {
        if (isTotalAmountNotMatch(requestTotalAmount, totalAmount)) {
            throw new BusinessException(ErrorType.ORDER_AMOUNT_NOT_MATCH_EXCEPTION);
        }
    }

    private boolean isTotalAmountNotMatch(int requestTotalAmount, int totalAmount) {
        return requestTotalAmount != totalAmount;
    }
}

package org.tattour.server.domain.order.domain;

import lombok.Getter;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@Getter
public class PurchaseRequest {
    private final Integer stickerId;
    private final Integer count;

    private PurchaseRequest(Integer stickerId, Integer count) {
        validate();
        this.stickerId = stickerId;
        this.count = count;
    }

    private void validate() {
        if (!isValidRequest()) {
            throw new BusinessException(ErrorType.INVALID_ORDERSHEET_ARGUMENT_EXCEPTION);
        }
    }

    private boolean isValidRequest() {
        return (stickerId == null && count == null) || (stickerId != null && count != null);
    }


    public static PurchaseRequest of(Integer stickerId, Integer count) {
        return new PurchaseRequest(stickerId, count);
    }

    public boolean isCartPurchase() {
        return stickerId == null && count == null;
    }
}

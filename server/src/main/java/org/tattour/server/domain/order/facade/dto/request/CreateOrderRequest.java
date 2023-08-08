package org.tattour.server.domain.order.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateOrderRequest {

    private Integer userId;
    private Integer stickerId;
    private Integer productCount;
    private Integer shippingFee;
    private Integer totalAmount;
    private String recipientName;
    private String contact;
    private String mailingAddress;
    private String baseAddress;
    private String detailAddress;

    public static CreateOrderRequest of(
            Integer userId,
            Integer stickerId,
            Integer productCount,
            Integer shippingFee,
            Integer totalAmount,
            String recipientName,
            String contact,
            String mailingAddress,
            String baseAddress,
            String detailAddress) {
        return new CreateOrderRequest(
                userId,
                stickerId,
                productCount,
                shippingFee,
                totalAmount,
                recipientName,
                contact,
                mailingAddress,
                baseAddress,
                detailAddress);
    }
}

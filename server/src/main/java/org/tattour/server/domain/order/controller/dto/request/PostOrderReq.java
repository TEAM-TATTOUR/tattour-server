package org.tattour.server.domain.order.controller.dto.request;

import lombok.Getter;

@Getter
public class PostOrderReq {
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
}

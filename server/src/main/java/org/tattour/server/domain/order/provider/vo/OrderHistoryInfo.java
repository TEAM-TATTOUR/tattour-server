package org.tattour.server.domain.order.provider.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderHistoryInfo {

    private int id;
    private String productName;
    private String productSize;
    private String productImageUrl;
    private int productAmount;
    private int productCount;
    private int shippingFee;
    private int totalAmount;
    private String recipientName;
    private String contact;
    private String mailingAddress;
    private String baseAddress;
    private String detailAddress;
    private String status;
    private String createdAt;
    private String lastUpdatedAt;
    private Boolean state;
    private int userId;
    private int stickerId;
}

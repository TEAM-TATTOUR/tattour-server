package org.tattour.server.domain.order.provider.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "user 결제 내역 Response")
@Getter
@Setter
@NoArgsConstructor
public class GetUserOrderHistoryRes {

    @Schema(description = "결제 내역 id")
    private int id;

    @Schema(description = "상품명", example = "포효하는 호랑이")
    private String productName;

    @Schema(description = "상품 크기", example = "")
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

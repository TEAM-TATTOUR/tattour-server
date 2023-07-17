package org.tattour.server.domain.order.controller.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostOrderReq {
    @NotNull(message = "userId is null")
    private Integer userId;

    @NotNull(message = "stickerId is null")
    private Integer stickerId;

    @NotNull(message = "productCount is null")
    @Min(value = 1)
    private Integer productCount;

    @NotNull(message = "shippingFee is null")
    @Min(value = 0)
    private Integer shippingFee;

    @NotNull(message = "totalAmount is null")
    @Min(value = 0)
    private Integer totalAmount;

    @NotBlank(message = "recipientName is required")
    @Size(max = 20, message = "recipientName is max 20")
    private String recipientName;

    @NotBlank(message = "contact is required")
    @Pattern(regexp = "\\d+", message = "숫자만 입력해주세요.")
    private String contact;

    @NotBlank(message = "mailingAddress is required")
    @Size(max = 10, message = "mailingAddress is max 10")
    private String mailingAddress;

    @NotBlank(message = "baseAddress is required")
    @Size(max = 100, message = "baseAddress is max 100")
    private String baseAddress;

    private String detailAddress;
}

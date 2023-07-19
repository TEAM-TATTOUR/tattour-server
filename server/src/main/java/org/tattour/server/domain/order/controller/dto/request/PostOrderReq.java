package org.tattour.server.domain.order.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;

@Schema(description = "결제하기 Request")
@Getter
public class PostOrderReq {

    @Schema(description = "타투 스티커 Id")
    @NotNull(message = "stickerId is null")
    private Integer stickerId;

    @Schema(description = "상품 개수", example = "3")
    @NotNull(message = "productCount is null")
    @Min(value = 1)
    private Integer productCount;

    @Schema(description = "배송비", example = "3000")
    @NotNull(message = "shippingFee is null")
    @Min(value = 0)
    private Integer shippingFee;

    @Schema(description = "총 주문금액", example = "12000")
    @NotNull(message = "totalAmount is null")
    @Min(value = 0)
    private Integer totalAmount;

    @Schema(description = "수신인 이름", example = "recipientName")
    @NotBlank(message = "recipientName is required")
    @Size(max = 20, message = "recipientName is max 20")
    private String recipientName;

    @Schema(description = "연락처", example = "01012345678")
    @NotBlank(message = "contact is required")
    @Pattern(regexp = "\\d+", message = "숫자만 입력해주세요.")
    private String contact;

    @Schema(description = "우편번호", example = "03963")
    @NotBlank(message = "mailingAddress is required")
    @Size(max = 10, message = "mailingAddress is max 10")
    private String mailingAddress;

    @Schema(description = "기본주소", example = "서울특별시 마포구 방울내로 90 (망원동)")
    @NotBlank(message = "baseAddress is required")
    @Size(max = 100, message = "baseAddress is max 100")
    private String baseAddress;

    @Schema(description = "상세주소", example = "2층")
    private String detailAddress;
}

package org.tattour.server.domain.order.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "결제 시트 불러오기 Request")
@Getter
@NoArgsConstructor
public class GetOrderSheetReq {

    @Schema(description = "타투 스티커 Id")
    @NotNull(message = "stickerId is null")
    private Integer stickerId;

    @Schema(description = "상품 개수", example = "3")
    @NotNull(message = "count is null")
    private Integer count;

    @Schema(description = "배송비", example = "3000")
    @NotNull(message = "shippingFee is null")
    private Integer shippingFee;
}

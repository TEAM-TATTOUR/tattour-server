package org.tattour.server.domain.order.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import org.tattour.server.domain.order.domain.OrderStatus;

@Schema(description = "주문상태 갱신 Request")
@Getter
public class PatchOrderStatusReq {
    @Schema(description = "주문상태", example = "상품준비중/주문취소/주문접수/배송중/배송완료")
    @NotNull(message = "orderStatus is null")
    private OrderStatus orderStatus;
}

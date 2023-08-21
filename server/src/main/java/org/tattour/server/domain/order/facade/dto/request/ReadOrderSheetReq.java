package org.tattour.server.domain.order.facade.dto.request;

import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadOrderSheetReq {

    @NotNull(message = "userId is null")
    private Integer userId;
    @NotNull(message = "stickerId is null")
    private Integer stickerId;

    @NotNull(message = "count is null")
    private Integer count;

    @NotNull(message = "shippingFee is null")
    private Integer shippingFee;

    public static ReadOrderSheetReq of(Integer userId, Integer stickerId, Integer count,
            Integer shippingFee) {
        return new ReadOrderSheetReq(userId, stickerId, count, shippingFee);
    }
}

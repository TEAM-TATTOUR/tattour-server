package org.tattour.server.domain.order.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.provider.vo.OrderAmountDetailRes;
import org.tattour.server.domain.user.provider.vo.UserProfileRes;

@Schema(description = "결제 페이지 불러오기")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadOrderSheetRes {
    private UserProfileRes userProfileRes;
    private List<OrderSheetStickerRes> orderSheetStickersRes;
    private OrderAmountDetailRes orderAmountDetailRes;

    public static ReadOrderSheetRes of(
            UserProfileRes userProfileRes,
            List<OrderSheetStickerRes> orderSheetStickersRes,
            OrderAmountDetailRes orderAmountDetailRes) {
        return new ReadOrderSheetRes(userProfileRes, orderSheetStickersRes, orderAmountDetailRes);
    }
}

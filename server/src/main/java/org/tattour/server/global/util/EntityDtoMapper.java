package org.tattour.server.global.util;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.provider.dto.response.GetOrderHistoryRes;
import org.tattour.server.domain.order.provider.dto.response.GetUserOrderHistoryRes;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.provider.dto.response.GetPointChargeRequestRes;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.dto.response.StickerLikedInfo;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.dto.response.GetUserProfileRes;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EntityDtoMapper {
    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    // User
    GetUserProfileRes toGetUserProfileRes(User user);

    // StickerLikedInfo
    @Mapping(target = "discountPrice",
            expression = "java(sticker.getDiscount() != null ? "
                    + "sticker.getPrice() * (100 - sticker.getDiscount().getDiscountRate()) / 100 "
                    + ": null)")
    @Mapping(target = "discountRate",
            expression = "java(sticker.getDiscount() != null ? "
                    + "sticker.getDiscount().getDiscountRate() : null)")
    StickerLikedInfo toStickerLikedInfo(Sticker sticker);
    List<StickerLikedInfo> toStickerLikedInfoList(List<Sticker> stickerList);

    // Order
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "stickerId", source = "sticker.id")
    GetUserOrderHistoryRes toGetUserOrderHistoryRes(Order order);
    List<GetUserOrderHistoryRes> toGetUserOrderHistoryListRes(List<Order> orderList);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "stickerId", source = "sticker.id")
    GetOrderHistoryRes toGetOrderHistoryRes(Order order);
    @IterableMapping(elementTargetType = GetOrderHistoryRes.class)
    List<GetOrderHistoryRes> toGetOrderHistoryListRes(Page<Order> orderList);

    // Point
    @Mapping(target = "userId", source = "user.id")
    GetPointChargeRequestRes toGetPointChargeRequestRes(PointChargeRequest pointChargeRequest);
    List<GetPointChargeRequestRes> toGetPointChargeRequestResList(List<PointChargeRequest> pointChargeRequestList);

}

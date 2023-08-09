package org.tattour.server.global.util;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.service.dto.response.CustomApplySummaryInfo;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.provider.vo.OrderHistoryInfo;
import org.tattour.server.domain.order.provider.vo.UserOrderHistoryInfo;
import org.tattour.server.domain.point.domain.PointChargeRequest;
import org.tattour.server.domain.point.provider.vo.PointChargeRequestInfo;
import org.tattour.server.domain.sticker.provider.vo.StickerLikedInfo;
import org.tattour.server.domain.user.domain.ProductLiked;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.dto.response.GetUserInfoDto;
import org.tattour.server.domain.user.facade.dto.response.ReadUserProfileRes;
import org.tattour.server.domain.user.provider.vo.UserProfileInfo;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EntityDtoMapper {

    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    // User
    UserProfileInfo toUserProfileInfo(User user);

    GetUserInfoDto toGetUserInfoDto(User user);

    // StickerLikedInfo
    @Mapping(target = "stickerId", source = "productLiked.sticker.id")
    @Mapping(target = "name", source = "productLiked.sticker.name")
    @Mapping(target = "price", source = "productLiked.sticker.price")
    @Mapping(target = "mainImageUrl", source = "productLiked.sticker.mainImageUrl")
    @Mapping(target = "discountPrice",
            expression = "java(productLiked.getSticker().getDiscount() != null ? "
                    + "productLiked.getSticker().getPrice() * (100 - productLiked.getSticker().getDiscount().getDiscountRate()) / 100 "
                    + ": null)")
    @Mapping(target = "discountRate",
            expression = "java(productLiked.getSticker().getDiscount() != null ? "
                    + "productLiked.getSticker().getDiscount().getDiscountRate() : null)")
    StickerLikedInfo toStickerLikedInfo(ProductLiked productLiked);

    List<StickerLikedInfo> toStickerLikedInfoList(List<ProductLiked> productLiked);

    // Order
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "stickerId", source = "sticker.id")
    UserOrderHistoryInfo toGetUserOrderHistoryRes(Order order);

    List<UserOrderHistoryInfo> toGetUserOrderHistoryListRes(List<Order> orderList);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "stickerId", source = "sticker.id")
    OrderHistoryInfo toGetOrderHistoryRes(Order order);

    @IterableMapping(elementTargetType = OrderHistoryInfo.class)
    List<OrderHistoryInfo> toGetOrderHistoryListRes(Page<Order> orderList);

    // Point
    @Mapping(target = "userId", source = "user.id")
    PointChargeRequestInfo toGetPointChargeRequestRes(PointChargeRequest pointChargeRequest);

    List<PointChargeRequestInfo> toGetPointChargeRequestResList(
            List<PointChargeRequest> pointChargeRequestList);

    // Custom
    CustomApplySummaryInfo toCustomApplySummaryInfo(Custom custom);

    List<CustomApplySummaryInfo> toCustomApplySummaryInfoList(List<Custom> customList);
}

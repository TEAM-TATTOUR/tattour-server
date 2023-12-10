package org.tattour.server.global.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.tattour.server.domain.cart.controller.dto.response.CartItemRes;
import org.tattour.server.domain.cart.model.Cart;
import org.tattour.server.domain.custom.facade.dto.response.CreateCustomSummaryRes;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomSummaryRes;
import org.tattour.server.domain.custom.model.Custom;
import org.tattour.server.domain.order.controller.dto.response.OrderSheetStickerRes;
import org.tattour.server.domain.order.model.OrderHistory;
import org.tattour.server.domain.order.provider.vo.OrderAmountDetailRes;
import org.tattour.server.domain.order.provider.vo.OrderHistoryInfo;
import org.tattour.server.domain.order.provider.vo.UserOrderHistoryInfo;
import org.tattour.server.domain.sticker.model.Sticker;
import org.tattour.server.domain.sticker.provider.vo.StickerLikedInfo;
import org.tattour.server.domain.sticker.provider.vo.StickerOrderInfo;
import org.tattour.server.domain.user.model.ProductLiked;
import org.tattour.server.domain.user.model.User;
import org.tattour.server.domain.user.provider.vo.HomeUserInfo;
import org.tattour.server.domain.user.provider.vo.UserProfileRes;

@org.mapstruct.Mapper(componentModel = "spring")
public interface EntityDtoMapper {

    EntityDtoMapper INSTANCE = Mappers.getMapper(EntityDtoMapper.class);

    // User
    HomeUserInfo toHomeUserInfo(User user);

    @Mapping(target = "id", source = "user.id")
    UserProfileRes toUserProfileInfo(User user);

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
    // todo: OrderedProduct 변경에 맞춰 수정하기
    @Mapping(target = "userId", source = "user.id")
//    @Mapping(target = "stickerId", source = "sticker.id")
    UserOrderHistoryInfo toGetUserOrderHistoryRes(OrderHistory orderHistory);

    List<UserOrderHistoryInfo> toGetUserOrderHistoryListRes(List<OrderHistory> orderHistoryList);

    // todo: OrderedProduct 변경에 맞춰 수정하기
    @Mapping(target = "userId", source = "user.id")
//    @Mapping(target = "stickerId", source = "sticker.id")
    @Mapping(target = "orderStatus", source = "orderStatus.value")
    OrderHistoryInfo toOrderHistoryInfo(OrderHistory orderHistory);

    @IterableMapping(elementTargetType = OrderHistoryInfo.class)
    List<OrderHistoryInfo> toOrderHistoryInfoPage(Page<OrderHistory> orderPage);

    default List<OrderSheetStickerRes> toOrderSheetStickerRes(StickerOrderInfo stickerOrderInfo) {
        return stickerOrderInfo.getStickerOrderInfos()
                .entrySet()
                .stream()
                .map(entry -> OrderSheetStickerRes.of(
                        entry.getKey().getMainImageUrl(),
                        entry.getKey().getName(),
                        entry.getKey().getPrice(),
                        entry.getKey().getDiscountPrice(),
                        entry.getValue()))
                .collect(Collectors.toList());
    }

    default OrderAmountDetailRes toOrderAmountRes(StickerOrderInfo stickerOrderInfo, int shippingFee) {
        int productAmount = stickerOrderInfo.getStickerOrderInfos()
                .entrySet()
                .stream()
                .mapToInt(entry -> calculateProductAmount(entry.getKey(), entry.getValue()))
                .sum();
        int totalAmount = calculateTotalAmount(productAmount, shippingFee);

        return OrderAmountDetailRes.of(totalAmount, productAmount, shippingFee);
    }

    private int calculateProductAmount(Sticker sticker, int count) {
        int price = Objects.isNull(sticker.getDiscountPrice()) ? sticker.getPrice() : sticker.getDiscountPrice();
        return price * count;
    }

    private int calculateTotalAmount(int productAmount, int shippingFee) {
        return productAmount + shippingFee;
    }


    // Custom
    CreateCustomSummaryRes toCustomApplySummaryInfo(Custom custom);

    List<CreateCustomSummaryRes> toCustomApplySummaryInfoList(List<Custom> customList);

    @Mapping(target = "imageUrl", source = "mainImageUrl")
    ReadCustomSummaryRes toReadCustomSummaryRes(Custom custom);

    List<ReadCustomSummaryRes> toReadCustomSummaryResList(List<Custom> customList);


    // Cart
    @Mapping(target = "stickerId", source = "cart.sticker.id")
    @Mapping(target = "mainImageUrl", source = "cart.sticker.mainImageUrl")
    @Mapping(target = "name", source = "cart.sticker.name")
    @Mapping(target = "price", source = "cart.sticker.price")
    @Mapping(target = "discountPrice", source = "cart.sticker.discountPrice")
    CartItemRes toCartItemRes(Cart cart);

    List<CartItemRes> toCartItemsRes(List<Cart> carts);
}

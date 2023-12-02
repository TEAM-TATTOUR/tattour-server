package org.tattour.server.domain.order.facade.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.order.controller.dto.response.ReadOrderSheetRes;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.domain.OrderStatus;
import org.tattour.server.domain.order.facade.OrderFacade;
import org.tattour.server.domain.order.facade.dto.request.CreateOrderRequest;
import org.tattour.server.domain.order.facade.dto.request.ReadOrderSheetReq;
import org.tattour.server.domain.order.facade.dto.request.UpdateOrderStatusReq;
import org.tattour.server.domain.order.facade.dto.response.ReadOrderHistoryListRes;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.domain.order.provider.vo.OrderAmountInfo;
import org.tattour.server.domain.order.provider.vo.OrderHistoryPageInfo;
import org.tattour.server.domain.order.service.impl.OrderServiceImpl;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.impl.StickerProviderImpl;
import org.tattour.server.domain.sticker.provider.vo.ReadOrderSheetStickerInfo;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.provider.vo.UserProfileInfo;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.util.EntityDtoMapper;
import org.tattour.server.infra.discord.service.DiscordMessageService;

@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {

    private final OrderProviderImpl orderProvider;
    private final OrderServiceImpl orderService;
    private final StickerProviderImpl stickerProvider;
    private final UserProviderImpl userProvider;
    private final DiscordMessageService discordMessageService;

    @Override
    @Transactional
    public ReadOrderSheetRes readOrderSheet(ReadOrderSheetReq req) {
        User user = userProvider.readUserById(req.getUserId());
        Sticker sticker = stickerProvider.getById(req.getStickerId());

        // 유저 프로필 정보
        UserProfileInfo userProfileInfo = userProvider.readUserProfileInfo(user);

        // 스티커 정보(배너이미지, 이름, 원래가격, 할인가격) + 개수
        ReadOrderSheetStickerInfo readOrderSheetStickerInfo = stickerProvider.readOrderSheetStickerInfo(sticker);
        readOrderSheetStickerInfo.setCount(req.getCount());

        // 결제 금액 정보
        // 총 결제 금액, 총 상품 금액, 배송비
        OrderAmountInfo orderAmountInfo =
                orderProvider.readOrderAmountRes(
                        Objects.isNull(sticker.getDiscountPrice())
                                ? sticker.getPrice()
                                : sticker.getDiscountPrice(),
                        req.getCount(),
                        req.getShippingFee());

        return ReadOrderSheetRes.of(userProfileInfo, readOrderSheetStickerInfo, orderAmountInfo);
    }

    @Override
    @Transactional
    public void createOrder(CreateOrderRequest req) {
        User user = userProvider.readUserById(req.getUserId());
        Sticker sticker = stickerProvider.getById(req.getStickerId());
        
        Order order = orderService.saveOrder(
                Order.of(
                        sticker.getName(),
                        sticker.getSize(),
                        sticker.getMainImageUrl(),
                        sticker.getPrice(),
                        req.getProductCount(),
                        req.getShippingFee(),
                        req.getTotalAmount(),
                        req.getRecipientName(),
                        req.getContact(),
                        req.getMailingAddress(),
                        req.getBaseAddress(),
                        req.getDetailAddress(),
                        user,
                        sticker));

        discordMessageService.sendOrderStickerMessage(order);
    }

    @Override
    @Transactional
    public ReadUserOrderHistoryListRes readOrderHistoryByUserId(Integer userId) {
        return orderProvider.readOrderHistoryByUserId(userId);
    }

    @Override
    public ReadOrderHistoryListRes readOrderHistoryOnPage(int page) {
        Page<Order> orderHistoryInfoPage = orderProvider.readOrderHistoryByPage(page);

        return ReadOrderHistoryListRes.of(
                EntityDtoMapper.INSTANCE.toOrderHistoryInfoPage(orderHistoryInfoPage),
                OrderHistoryPageInfo.of(
                        page,
                        orderHistoryInfoPage.getTotalElements(),
                        orderHistoryInfoPage.getTotalPages()));
    }

    @Override
    @Transactional
    public void updateOrderStatus(UpdateOrderStatusReq req) {
        Order order = orderProvider.readOrderById(req.getOrderId());
        OrderStatus requestedStatus = req.getOrderStatus();

        if (order.getOrderStatus().equals(requestedStatus)) {
            if (requestedStatus.equals(OrderStatus.CANCEL)) {
                throw new BusinessException(ErrorType.ALREADY_CANCELED_ORDER_HISTORY_EXCEPTION);
            }
        }

        order.setOrderStatus(req.getOrderStatus());
        orderService.saveOrder(order);
    }
}

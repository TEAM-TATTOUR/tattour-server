package org.tattour.server.domain.order.facade.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.cart.domain.Cart;
import org.tattour.server.domain.cart.service.CartService;
import org.tattour.server.domain.order.controller.dto.response.OrderSheetStickerRes;
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
import org.tattour.server.domain.order.provider.vo.OrderAmountDetailRes;
import org.tattour.server.domain.order.provider.vo.OrderHistoryPageInfo;
import org.tattour.server.domain.order.service.impl.OrderServiceImpl;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.impl.StickerProviderImpl;
import org.tattour.server.domain.sticker.provider.vo.StickerOrderInfo;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.provider.vo.UserProfileRes;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.util.EntityDtoMapper;
import org.tattour.server.infra.discord.service.DiscordMessageService;

@Service
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {
    public final static int SHIPPING_FEE = 3000;

    private final OrderProviderImpl orderProvider;
    private final OrderServiceImpl orderService;
    private final CartService cartService;
    private final StickerProviderImpl stickerProvider;
    private final UserProviderImpl userProvider;
    private final DiscordMessageService discordMessageService;

    //todo : 나중에 배달 지역별로 배송비 책정하기
    @Override
    @Transactional
    public ReadOrderSheetRes readOrderSheet(ReadOrderSheetReq req) {
        User user = userProvider.readUserById(req.getUserId());
        StickerOrderInfo stickerOrderInfo = getStickerOrderInfo(req);

        UserProfileRes userProfileRes =
                EntityDtoMapper.INSTANCE.toUserProfileInfo(user);

        List<OrderSheetStickerRes> orderSheetStickersRes =
                EntityDtoMapper.INSTANCE.toOrderSheetStickerRes(stickerOrderInfo);

        OrderAmountDetailRes orderAmountDetailRes =
                EntityDtoMapper.INSTANCE.toOrderAmountRes(stickerOrderInfo, SHIPPING_FEE);

        return ReadOrderSheetRes.of(userProfileRes, orderSheetStickersRes, orderAmountDetailRes);
    }

    private StickerOrderInfo getStickerOrderInfo(ReadOrderSheetReq req) {
        if (req.isCartOrder()) {
            List<Cart> carts = cartService.findByUserId(req.getUserId());
            return stickerProvider.getStickerOrderInfoFromCart(carts);
        } else {
            return stickerProvider.getStickerOrderInfoFromOrder(req.getStickerId(), req.getCount());
        }
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

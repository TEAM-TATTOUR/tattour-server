package org.tattour.server.domain.order.facade.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.cart.model.Cart;
import org.tattour.server.domain.cart.service.CartService;
import org.tattour.server.domain.order.controller.dto.response.OrderSheetStickerRes;
import org.tattour.server.domain.order.controller.dto.response.ReadOrderSheetRes;
import org.tattour.server.domain.order.facade.OrderFacade;
import org.tattour.server.domain.order.facade.dto.request.CreateOrderReq;
import org.tattour.server.domain.order.facade.dto.request.UpdateOrderStatusReq;
import org.tattour.server.domain.order.facade.dto.response.ReadOrderHistoryListRes;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderHistoryListRes;
import org.tattour.server.domain.order.model.OrderAmount;
import org.tattour.server.domain.order.model.OrderHistory;
import org.tattour.server.domain.order.model.OrderStatus;
import org.tattour.server.domain.order.model.OrderedProduct;
import org.tattour.server.domain.order.model.PurchaseRequest;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.domain.order.provider.vo.OrderAmountDetailRes;
import org.tattour.server.domain.order.provider.vo.OrderHistoryPageInfo;
import org.tattour.server.domain.order.service.impl.OrderServiceImpl;
import org.tattour.server.domain.sticker.provider.impl.StickerProviderImpl;
import org.tattour.server.domain.sticker.provider.vo.StickerOrderInfo;
import org.tattour.server.domain.user.model.User;
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

    //todo : 배달 지역별로 배송비 책정하기?
    @Override
    @Transactional
    public ReadOrderSheetRes readOrderSheet(int userId, PurchaseRequest purchaseRequest) {
        User user = userProvider.readUserById(userId);
        StickerOrderInfo stickerOrderInfo = getStickerOrderInfo(user, purchaseRequest);

        UserProfileRes userProfileRes =
                EntityDtoMapper.INSTANCE.toUserProfileInfo(user);

        List<OrderSheetStickerRes> orderSheetStickersRes =
                EntityDtoMapper.INSTANCE.toOrderSheetStickerRes(stickerOrderInfo);

        OrderAmountDetailRes orderAmountDetailRes =
                EntityDtoMapper.INSTANCE.toOrderAmountRes(stickerOrderInfo, SHIPPING_FEE);

        return ReadOrderSheetRes.of(userProfileRes, orderSheetStickersRes, orderAmountDetailRes);
    }

    private StickerOrderInfo getStickerOrderInfo(User user, PurchaseRequest purchaseRequest) {
        if (purchaseRequest.isCartPurchase()) {
            List<Cart> carts = cartService.findByUser(user);
            if (carts.isEmpty()) {
                throw new BusinessException(ErrorType.NOT_FOUND_CARTS_EXCEPTION);
            }
            return getCartStickersOrderInfo(carts);
        }

        return getSingleStickerOrderInfo(purchaseRequest.getStickerId(),
                purchaseRequest.getCount());
    }

    private StickerOrderInfo getSingleStickerOrderInfo(int stickerId, int count) {
        return stickerProvider.getStickerOrderInfoFromOrder(stickerId, count);
    }

    private StickerOrderInfo getCartStickersOrderInfo(List<Cart> carts) {
        return stickerProvider.getStickerOrderInfoFromCart(carts);
    }

    @Override
    @Transactional
    public void order(PurchaseRequest purchaseRequest, CreateOrderReq orderReq) {
        User user = userProvider.readUserById(orderReq.getUserId());
        StickerOrderInfo stickerOrderInfo = getStickerOrderInfo(user, purchaseRequest);
        OrderAmount orderAmount = OrderAmount.calculate(
                stickerOrderInfo,
                orderReq.getTotalAmount(),
                orderReq.getShippingFee());

        OrderHistory orderHistory = orderService.saveOrder(
                OrderHistory.builder()
                        .productAmount(orderAmount.getProductAmount())
                        .shippingFee(orderAmount.getShippingFee())
                        .totalAmount(orderAmount.getTotalAmount())
                        .recipientName(orderReq.getRecipientName())
                        .contact(orderReq.getContact())
                        .mailingAddress(orderReq.getMailingAddress())
                        .baseAddress(orderReq.getBaseAddress())
                        .detailAddress(orderReq.getDetailAddress())
                        .user(user)
                        .build());

        List<OrderedProduct> orderedProducts = orderService.saveOrderedProducts(orderHistory,
                stickerOrderInfo);

        if (purchaseRequest.isCartPurchase()) {
            cartService.deleteAllByUserId(user);
        }

        discordMessageService.sendOrderStickerMessage(orderHistory, orderedProducts);
    }

    @Override
    @Transactional
    public ReadUserOrderHistoryListRes readOrderHistoryByUserId(Integer userId) {
        return orderProvider.readOrderHistoryByUserId(userId);
    }

    @Override
    public ReadOrderHistoryListRes readOrderHistoryOnPage(int page) {
        Page<OrderHistory> orderHistoryInfoPage = orderProvider.readOrderHistoryByPage(page);

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
        OrderHistory orderHistory = orderProvider.readOrderHistoryById(req.getOrderId());
        OrderStatus requestedStatus = req.getOrderStatus();

        if (orderHistory.getOrderStatus().equals(requestedStatus)) {
            if (requestedStatus.equals(OrderStatus.CANCEL)) {
                throw new BusinessException(ErrorType.ALREADY_CANCELED_ORDER_HISTORY_EXCEPTION);
            }
        }

        orderHistory.setOrderStatus(req.getOrderStatus());
        orderService.saveOrder(orderHistory);
    }
}

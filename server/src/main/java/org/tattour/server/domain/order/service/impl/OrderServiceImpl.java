package org.tattour.server.domain.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.order.controller.dto.request.PostOrderReq;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.provider.impl.OrderProviderImpl;
import org.tattour.server.domain.order.repository.impl.OrderRepositoryImpl;
import org.tattour.server.domain.order.service.OrderService;
import org.tattour.server.domain.order.service.dto.request.PostOrderReqDto;
import org.tattour.server.domain.order.service.dto.request.UpdateOrderStatusReq;
import org.tattour.server.domain.point.service.dto.request.SaveUserPointLogReq;
import org.tattour.server.domain.point.service.impl.PointServiceImpl;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.impl.StickerProviderImpl;
import org.tattour.server.domain.order.domain.OrderStatus;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.dto.request.UpdateUserPointReq;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryImpl orderRepository;
    private final OrderProviderImpl orderProvider;
    private final StickerProviderImpl stickerProvider;
    private final UserProviderImpl userProvider;
    private final UserServiceImpl userService;
    private final PointServiceImpl pointService;

    @Override
    @Transactional
    public Order saveOrder(PostOrderReqDto req) {
        User user = userProvider.getUserById(req.getUserId());
        Sticker sticker = stickerProvider.getStickerById(req.getStickerId());
        Order order = Order.of(sticker.getName(), sticker.getSize(), sticker.getMainImageUrl(),
                sticker.getPrice(), req.getProductCount(), req.getShippingFee(),
                req.getTotalAmount(),
                req.getRecipientName(), req.getContact(), req.getMailingAddress(),
                req.getBaseAddress(),
                req.getDetailAddress(), user, sticker);

        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void updateOrderStatus(UpdateOrderStatusReq req) {
        Order order = orderProvider.getOrderById(req.getId());

        // 주문취소일 경우
        if (req.getOrderStatus().equals(OrderStatus.CANCEL)) {
            if (!order.getOrderStatus().equals(OrderStatus.CANCEL)) {
                // 상태 변경
                order.setOrderStatus(req.getOrderStatus());
                orderRepository.save(order);
                // 유저 포인트 변경
                userService.updateUserPoint(
                        UpdateUserPointReq.of(order.getUser().getId(), order.getTotalAmount()));
                // 포인트 로그 남기기
                pointService.savePointLog(
                        SaveUserPointLogReq
                                .of("결제 취소",
                                        order.getSticker().getName(),
                                        order.getTotalAmount(),
                                        order.getUser().getPoint(),
                                        order.getUser().getId()));
            } else {
                throw new BusinessException(ErrorType.ALREADY_CANCELED_ORDER_HISTORY_EXCEPTION);
            }
        } else {
            order.setOrderStatus(req.getOrderStatus());
        }
        orderRepository.save(order);
    }
}

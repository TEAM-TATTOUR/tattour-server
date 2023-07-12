package org.tattour.server.domain.order.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.order.controller.dto.request.PostOrderReq;
import org.tattour.server.domain.order.domain.Order;
import org.tattour.server.domain.order.repository.impl.OrderRepositoryImpl;
import org.tattour.server.domain.order.service.OrderService;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.impl.StickerProviderImpl;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepositoryImpl orderRepository;
    private final StickerProviderImpl stickerProvider;
    private final UserProviderImpl userProvider;

    @Override
    @Transactional
    public void saveOrder(PostOrderReq req) {
        User user = userProvider.getUserById(req.getUserId());
        Sticker sticker = stickerProvider.getStickerById(req.getStickerId());
        Order order = Order.of(sticker.getName(), sticker.getSize(), sticker.getMainImageUrl(),
                sticker.getPrice(), req.getProductCount(), req.getShippingFee(), req.getTotalAmount(),
                req.getRecipientName(), req.getContact(), req.getMailingAddress(), req.getBaseAddress(),
                req.getDetailAddress(), user, sticker);

        orderRepository.save(order);
    }
}

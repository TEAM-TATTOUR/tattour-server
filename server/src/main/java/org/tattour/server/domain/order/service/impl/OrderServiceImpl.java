package org.tattour.server.domain.order.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.order.domain.OrderHistory;
import org.tattour.server.domain.order.domain.OrderedProduct;
import org.tattour.server.domain.order.repository.impl.OrderRepositoryImpl;
import org.tattour.server.domain.order.repository.impl.OrderedProductRepositoryImpl;
import org.tattour.server.domain.order.service.OrderService;
import org.tattour.server.domain.sticker.provider.vo.StickerOrderInfo;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepositoryImpl orderRepository;
    private final OrderedProductRepositoryImpl orderedProductRepository;

    @Override
    @Transactional
    public OrderHistory saveOrder(OrderHistory orderHistory) {
        return orderRepository.save(orderHistory);
    }

    @Override
    public void saveOrderedProducts(OrderHistory orderHistory, StickerOrderInfo stickerOrderInfo) {
        List<OrderedProduct> orderedProducts = stickerOrderInfo.getStickerOrderInfos()
                .entrySet()
                .stream()
                .map(entry -> OrderedProduct.builder()
                        .name(entry.getKey().getName())
                        .price(entry.getKey().getPrice())
                        .count(entry.getValue())
                        .mainImageUrl(entry.getKey().getMainImageUrl())
                        .sticker(entry.getKey())
                        .orderHistory(orderHistory)
                        .build())
                .collect(Collectors.toUnmodifiableList());

        orderedProductRepository.saveAll(orderedProducts);
    }
}

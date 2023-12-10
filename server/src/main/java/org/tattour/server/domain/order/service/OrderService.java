package org.tattour.server.domain.order.service;

import org.tattour.server.domain.order.model.OrderHistory;
import org.tattour.server.domain.sticker.provider.vo.StickerOrderInfo;

public interface OrderService {
    OrderHistory saveOrder(OrderHistory orderHistory);

    void saveOrderedProducts(OrderHistory orderHistory, StickerOrderInfo stickerOrderInfo);
}

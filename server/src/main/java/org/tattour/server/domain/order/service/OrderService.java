package org.tattour.server.domain.order.service;

import java.util.List;
import org.tattour.server.domain.order.model.OrderHistory;
import org.tattour.server.domain.order.model.OrderedProduct;
import org.tattour.server.domain.sticker.provider.vo.StickerOrderInfo;

public interface OrderService {

    OrderHistory saveOrder(OrderHistory orderHistory);

    List<OrderedProduct> saveOrderedProducts(OrderHistory orderHistory,
            StickerOrderInfo stickerOrderInfo);
}

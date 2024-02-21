package org.tattour.server.infra.discord.dto.resquest;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.model.OrderHistory;
import org.tattour.server.domain.order.model.OrderedProduct;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderStickerDiscordMessage {

    String title;
    String description;

    public static OrderStickerDiscordMessage from(
            OrderHistory orderHistory,
            List<OrderedProduct> orderedProducts) {
        String title = "주문 번호: " + orderHistory.getId();
        StringBuilder description = new StringBuilder();

        description.append("\n\n### [주문 정보]")
                .append("\n- 유저 번호: ").append(orderHistory.getUser().getId())
                .append("\n- 상품 금액: ").append(orderHistory.getProductAmount())
                .append("\n- 총 주문 금액: ").append(orderHistory.getTotalAmount());

        description.append("\n\n### [주문 상품 내역]");
        orderedProducts.forEach(product -> {
            description.append("\n");
            description.append("\n- 상품 번호: ").append(product.getSticker().getId());
            description.append("\n- 상품명: ").append(product.getSticker().getName());
            description.append("\n- 가격: ").append(product.getPrice());
            description.append("\n- 수량: ").append(product.getCount());
        });

        description.append("\n\n### [배송 정보]")
                .append("\n- 수령인: ").append(orderHistory.getRecipientName())
                .append("\n- 연락처: ").append(orderHistory.getContact())
                .append("\n- 우편 번호: ").append(orderHistory.getMailingAddress())
                .append("\n- 기본 주소: ").append(orderHistory.getBaseAddress())
                .append("\n- 싱세 주소: ").append(orderHistory.getDetailAddress())
                .append("\n- 배송비: ").append(orderHistory.getShippingFee());

        return new OrderStickerDiscordMessage(title, description.toString());
    }
}

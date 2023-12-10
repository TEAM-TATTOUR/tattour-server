package org.tattour.server.infra.discord.dto.resquest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.model.OrderHistory;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderStickerDiscordMessage {

    String title;
    String description;
    DiscordCustomImage image;

    // todo: 디스코드 메시지 내용 형식 수정
    public static OrderStickerDiscordMessage from(OrderHistory orderHistory) {
//        String title = "Order Id : " + orderHistory.getId();
//        String description = "\n유저 Id : " + orderHistory.getUser().getId()
//                + "\n수령인 : " + orderHistory.getRecipientName()
//                + "\n연락처 : " + orderHistory.getContact()
//                + "\n유저 이메일 : " + orderHistory.getMailingAddress()
//                + "\n상품 Id : " + orderHistory.getProductName()
//                + "\n상품 이름 : " + orderHistory.getProductName()
//                + "\n상품 크기 : " + orderHistory.getProductSize()
//                + "\n상품 금액 : " + orderHistory.getProductAmount()
//                + "\n상품 개수 : " + orderHistory.getProductCount()
//                + "\n총 주문 금액 : " + orderHistory.getTotalAmount()
//                + "\n기본 주소 : " + orderHistory.getBaseAddress()
//                + "\n싱세 주소 : " + orderHistory.getDetailAddress()
//                + "\n배송비 : " + orderHistory.getShippingFee();
//        return new OrderStickerDiscordMessage(
//                title,
//                description,
//                new DiscordCustomImage(orderHistory.getProductImageUrl())
//        );
        return new OrderStickerDiscordMessage(
                "[임시 응답]",
                "메시지 형식에 대한 추가 개발이 필요해 임시 응답을 전달합니다.",
                new DiscordCustomImage(null)
        );
    }
}

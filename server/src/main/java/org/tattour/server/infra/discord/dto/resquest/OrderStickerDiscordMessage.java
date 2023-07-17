package org.tattour.server.infra.discord.dto.resquest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.order.domain.Order;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderStickerDiscordMessage {

	String title;
	String description;
	DiscordCustomImage image;

	public static OrderStickerDiscordMessage from(Order order) {
		String title = "Order Id : " + order.getId();
		String description = "\n유저 Id : " + order.getUser().getId()
			+ "\n수령인 : " + order.getRecipientName()
			+ "\n연락처 : " + order.getContact()
			+ "\n유저 이메일 : " + order.getMailingAddress()
			+ "\n상품 Id : " + order.getProductName()
			+ "\n상품 이름 : " + order.getProductName()
			+ "\n상품 크기 : " + order.getProductSize()
			+ "\n상품 금액 : " + order.getProductAmount()
			+ "\n상품 개수 : " + order.getProductCount()
			+ "\n총 주문 금액 : " + order.getTotalAmount()
			+ "\n기본 주소 : " + order.getBaseAddress()
			+ "\n싱세 주소 : " + order.getDetailAddress()
			+ "\n배송비 : " + order.getShippingFee();
		return new OrderStickerDiscordMessage(
			title,
			description,
			new DiscordCustomImage(order.getProductImageUrl())
		);
	}
}

package org.tattour.server.infra.discord.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.custom.model.Custom;
import org.tattour.server.domain.order.model.OrderHistory;
import org.tattour.server.domain.order.model.OrderedProduct;
import org.tattour.server.domain.user.model.User;
import org.tattour.server.infra.discord.client.DiscordApiClient;
import org.tattour.server.infra.discord.dto.resquest.CustomApplyDiscordMessage;
import org.tattour.server.infra.discord.dto.resquest.DiscordMessageReq;
import org.tattour.server.infra.discord.dto.resquest.OrderStickerDiscordMessage;
import org.tattour.server.infra.discord.property.CustomWebhookProperty;
import org.tattour.server.infra.discord.property.OrderWebhookProperty;

@Component
@RequiredArgsConstructor
public class DiscordMessageService {

    private final DiscordApiClient discordApiClient;
    private final OrderWebhookProperty orderWebhookProperty;
    private final CustomWebhookProperty customWebhookProperty;

    @Transactional
    public void sendCustomApplyMessage(Custom custom) {
        CustomApplyDiscordMessage payload = CustomApplyDiscordMessage.from(custom.getUser(),
                custom);
        sendDiscordMessage(customWebhookProperty.getClientId(), customWebhookProperty.getToken(),
                custom.getUser(), "님이 커스텀 도안을 신청했습니다.", payload);
    }

    @Transactional
    public void sendOrderStickerMessage(OrderHistory orderHistory,
            List<OrderedProduct> orderedProducts) {
        OrderStickerDiscordMessage payload =
                OrderStickerDiscordMessage.from(orderHistory, orderedProducts);
        sendDiscordMessage(
                orderWebhookProperty.getClientId(),
                orderWebhookProperty.getToken(),
                orderHistory.getUser(),
                " 님이 스티커를 주문했습니다.",
                payload);
    }

    public void sendDiscordMessage(String clientId, String token, User user, String content,
            Object payload) {
        DiscordMessageReq request = DiscordMessageReq.from(user, content, payload);
        discordApiClient.sendMessage(clientId, token, request);
    }
}

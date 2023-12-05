package org.tattour.server.infra.discord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.order.domain.OrderHistory;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.infra.discord.client.DiscordApiClient;
import org.tattour.server.infra.discord.dto.resquest.CustomApplyDiscordMessage;
import org.tattour.server.infra.discord.dto.resquest.DiscordMessageReq;
import org.tattour.server.infra.discord.dto.resquest.OrderStickerDiscordMessage;
import org.tattour.server.infra.discord.dto.resquest.PointChargeDiscordMessage;
import org.tattour.server.infra.discord.property.CustomWebhookProperty;
import org.tattour.server.infra.discord.property.OrderWebhookProperty;
import org.tattour.server.infra.discord.property.PointWebhookProperty;

@Component
@RequiredArgsConstructor
public class DiscordMessageService {

    private final DiscordApiClient discordApiClient;
    private final OrderWebhookProperty orderWebhookProperty;
    private final CustomWebhookProperty customWebhookProperty;
    private final PointWebhookProperty pointWebhookProperty;

    @Transactional
    public Boolean sendPointChargeLogMessage(User user, Integer amount) {
        PointChargeDiscordMessage payload = PointChargeDiscordMessage.from(user, amount);
        sendDiscordMessage(pointWebhookProperty.getClientId(), pointWebhookProperty.getToken(),
                user, "님이 포인트 충전을 요구했습니다", payload);
        return true;
    }

    @Transactional
    public Boolean sendCustomApplyMessage(Custom custom) {
        CustomApplyDiscordMessage payload = CustomApplyDiscordMessage.from(custom.getUser(),
                custom);
        sendDiscordMessage(customWebhookProperty.getClientId(), customWebhookProperty.getToken(),
                custom.getUser(), "님이 커스텀 도안을 신청했습니다.", payload);
        return true;
    }

    @Transactional
    public Boolean sendOrderStickerMessage(OrderHistory orderHistory) {
        OrderStickerDiscordMessage payload = OrderStickerDiscordMessage.from(orderHistory);
        sendDiscordMessage(orderWebhookProperty.getClientId(), orderWebhookProperty.getToken(),
                orderHistory.getUser(), "님이 스티커를 주문했습니다.", payload);
        return true;
    }

    public void sendDiscordMessage(String clientId, String token, User user, String content,
                                   Object payload) {
        DiscordMessageReq request = DiscordMessageReq.from(user, content, payload);
        discordApiClient.sendMessage(clientId, token, request);
    }
}

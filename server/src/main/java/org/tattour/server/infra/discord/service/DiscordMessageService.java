package org.tattour.server.infra.discord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.service.UserService;
import org.tattour.server.infra.discord.client.DiscordApiClient;
import org.tattour.server.infra.discord.dto.resquest.CustomApplyDiscordMessage;
import org.tattour.server.infra.discord.dto.resquest.DiscordMessageReq;
import org.tattour.server.infra.discord.dto.resquest.PointChargeDiscordMessage;
import org.tattour.server.infra.discord.property.DiscordProperty;

@Component
@RequiredArgsConstructor
public class DiscordMessageService {

	private final DiscordApiClient discordApiClient;
	private final DiscordProperty discordProperty;

	@Transactional
	public boolean sendPointChargeLogMessage(User user, Integer amount) {
		PointChargeDiscordMessage payload = PointChargeDiscordMessage.from(user, amount);
		sendDiscordMessage(user, "님이 포인트 충전을 요구했습니다", payload);
		return true;
	}

	@Transactional
	public boolean sendCustomApplyMessage(Custom custom) {
		CustomApplyDiscordMessage payload = CustomApplyDiscordMessage.from(custom.getUser(), custom);
		sendDiscordMessage(custom.getUser(), "님이 커스텀 도안을 신청했습니다.", payload);
		return true;
	}

	public void sendDiscordMessage(User user, String content, Object payload) {
		DiscordMessageReq request = DiscordMessageReq.from(user, content, payload);
		discordApiClient.sendMessage(
			discordProperty.getClientId(),
			discordProperty.getToken(),
			request
		);
	}
}

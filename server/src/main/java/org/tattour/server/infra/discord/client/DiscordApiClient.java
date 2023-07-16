package org.tattour.server.infra.discord.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.tattour.server.infra.discord.dto.resquest.DiscordMessageReq;

@FeignClient(name = "discordApiClient", url = "https://discord.com/api/v10/webhooks")
public interface DiscordApiClient {

	@PostMapping(value = "/{webhookId}/{webhookToken}", consumes = MediaType.APPLICATION_JSON_VALUE)
	void sendMessage(
		@PathVariable(value = "webhookId") String webhookId,
		@PathVariable(value = "webhookToken") String webhookToken,
		@RequestBody DiscordMessageReq request
	);
}

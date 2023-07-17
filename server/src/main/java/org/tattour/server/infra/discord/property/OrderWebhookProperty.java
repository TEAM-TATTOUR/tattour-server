package org.tattour.server.infra.discord.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("discord.webhook.order")
public class OrderWebhookProperty {

	private String clientId;
	private String token;
}

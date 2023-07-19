package org.tattour.server.infra.discord.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties("discord.webhook.custom")
public class CustomWebhookProperty {

    private String clientId;
    private String token;
}

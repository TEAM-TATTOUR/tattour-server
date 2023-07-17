package org.tattour.server.infra.discord.dto.resquest;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.user.domain.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DiscordMessageReq {

	String username;
	String content;
	List<Object> embeds;

	public static DiscordMessageReq from(User user, String content, Object payload) {
		List<Object> embeds = new ArrayList<>();
		embeds.add(payload);
		return new DiscordMessageReq(user.getName(), content, embeds);
	}
}

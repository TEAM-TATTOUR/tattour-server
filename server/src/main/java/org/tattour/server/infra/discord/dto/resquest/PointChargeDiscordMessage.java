package org.tattour.server.infra.discord.dto.resquest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.user.domain.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PointChargeDiscordMessage {

	String title;
	String description;

	public static PointChargeDiscordMessage from(User user, Integer amount) {
		String description = amount + "원 송금 확인해주세요"
			+ "전화번호 : " + user.getPhoneNumber();
		return new PointChargeDiscordMessage("User Id : " + user.getId(), amount + "원 송금 확인해주세요");
	}

}

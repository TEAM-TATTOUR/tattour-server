package org.tattour.server.infra.discord.dto.resquest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.user.domain.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomApplyDiscordMessage {

    String title;
    String description;
    DiscordCustomImage image;

    public static CustomApplyDiscordMessage from(User user, Custom custom) {
        String title = "Custom Id : " + custom.getId();
        String description;
        if (custom.getHaveDesign()) {
            description = "유저 전화번호 : " + user.getPhoneNumber()
                    + "\n이름 : " + custom.getName()
                    + "\n사이즈 : " + custom.getSize().getSize()
                    + "\n추가 요청 사항 : " + custom.getDemand()
                    + "\n수량 : " + custom.getCount()
                    + "\n그려둔 도안 있음";
        } else {
            description = "유저 전화번호 : " + user.getPhoneNumber()
                    + "이름 : " + custom.getName()
                    + "\n사이즈 : " + custom.getSize().getSize()
                    + "\n추가 요청 사항 : " + custom.getDemand()
                    + "\n색상 : " + custom.getIsColored()
                    + "\n주제 및 설명 : " + custom.getDescription()
                    + "\n수량 : " + custom.getCount()
                    + "\n그려둔 도안 없음";
        }
        return new CustomApplyDiscordMessage(
                title,
                description,
                new DiscordCustomImage(custom.getMainImageUrl())
        );
    }
}

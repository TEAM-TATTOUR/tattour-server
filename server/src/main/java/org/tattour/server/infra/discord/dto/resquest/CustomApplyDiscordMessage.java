package org.tattour.server.infra.discord.dto.resquest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.custom.model.Custom;
import org.tattour.server.domain.user.model.User;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomApplyDiscordMessage {

    String title;
    String description;
    DiscordCustomImage image;

    public static CustomApplyDiscordMessage from(User user, Custom custom) {
        String title = "커스텀 도안 번호 : " + custom.getId();
        StringBuilder description = new StringBuilder();

        description.append("\n\n### [유저 정보]")
                .append("\n- 연락처: ").append(user.getPhoneNumber())
                .append("\n- 이름: ").append(custom.getName())
                .append("\n\n### [도안 신청 정보]")
                .append("\n- 사이즈: ").append(custom.getSize().getSize())
                .append("\n- 수량: ").append(custom.getCount());

        if (custom.getHaveDesign()) {
            description.append("\n- 추가 요청 사항: ").append(custom.getDemand())
                    .append("\n- 도안: ").append("`있음`");
        } else {
            description.append("\n- 색상: ").append(custom.getIsColored())
                    .append("\n- 주제 및 설명: \n").append(custom.getDescription())
                    .append("\n- 추가 요청 사항: ").append(custom.getDemand())
                    .append("\n- 도안: ").append("`없음`");
        }

        return new CustomApplyDiscordMessage(
                title,
                description.toString(),
                new DiscordCustomImage(custom.getMainImageUrl())
        );
    }
}

package org.tattour.server.domain.user.provider.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfileInfo {
    @Schema(description = "이름", example = "userName")
    private String name;

    @Schema(description = "포인트", example = "3000")
    private Integer point;

    public static UserProfileInfo of(String name, Integer point) {
        return new UserProfileInfo(name, point);
    }
}

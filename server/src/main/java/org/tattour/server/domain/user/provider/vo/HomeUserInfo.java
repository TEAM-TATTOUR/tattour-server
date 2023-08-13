package org.tattour.server.domain.user.provider.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "user 정보")
@Getter
@AllArgsConstructor
public class HomeUserInfo {
    @Schema(description = "이름", example = "userName")
    private String name;

    @Schema(description = "포인트", example = "3000")
    private Integer point;

    public static HomeUserInfo of(String name, Integer point) {
        return new HomeUserInfo(name, point);
    }
}

package org.tattour.server.domain.user.provider.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "user profile 정보 Request")
@Getter
@Setter
@AllArgsConstructor
public class GetUserProfileRes {

    @Schema(description = "이름", example = "userName")
    private String name;

    @Schema(description = "포인트", example = "3000")
    private Integer point;

    public static GetUserProfileRes of(String name, Integer point) {
        return new GetUserProfileRes(name, point);
    }
}

package org.tattour.server.domain.user.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "로그인 Response")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRes {

    @Schema(description = "user Id")
    private Integer userId;

    @Schema(description = "JWT access token")
    private String accessToken;

    @Schema(description = "user 회원가입 완료 여부")
    private boolean isUserExist;

    public static LoginRes of(Integer userId, String accessToken, boolean isUserExist) {
        return new LoginRes(userId, accessToken, isUserExist);
    }
}

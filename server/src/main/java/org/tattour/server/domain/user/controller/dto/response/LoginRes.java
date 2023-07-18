package org.tattour.server.domain.user.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Schema(description = "소셜 회원가입 / 로그인 response")
@Getter
@NoArgsConstructor
public class LoginRes {

    @Schema(description = "userId")
    private Integer userId;

    @Schema(description = "jwt access token")
    private String accessToken;

    public LoginRes(Integer userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }
    public static LoginRes of(Integer userId, String accessToken){
        return new LoginRes(userId, accessToken);
    }
}

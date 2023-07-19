package org.tattour.server.infra.socialLogin.client.kakao.service.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "회원가입 / 로그인 Response")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialLoginResponse {

    @Schema(description = "user Id")
    private Integer userId;

    @Schema(description = "회원가입 완료 여부")
    private boolean isUserExist;

    public static SocialLoginResponse of(Integer userId, boolean isUserExist) {
        return new SocialLoginResponse(userId, isUserExist);
    }
}

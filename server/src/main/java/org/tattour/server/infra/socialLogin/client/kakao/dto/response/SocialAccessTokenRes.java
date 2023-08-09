package org.tattour.server.infra.socialLogin.client.kakao.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialAccessTokenRes {
    private String accessToken;
    private String refreshToken;

    public static SocialAccessTokenRes of(String accessToken, String refreshToken) {
        return new SocialAccessTokenRes(accessToken, refreshToken);
    }
}

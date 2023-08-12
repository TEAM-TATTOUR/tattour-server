package org.tattour.server.infra.socialLogin.client.kakao.service.dto.request.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialAccessTokenRes {
    private String accessToken;
    private String refreshToken;

    public static SocialAccessTokenRes of(String accessToken, String refreshToken) {
        return new SocialAccessTokenRes(accessToken, refreshToken);
    }
}

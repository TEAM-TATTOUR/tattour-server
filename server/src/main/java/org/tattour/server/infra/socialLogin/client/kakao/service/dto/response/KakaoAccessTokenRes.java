package org.tattour.server.infra.socialLogin.client.kakao.service.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoAccessTokenRes extends SocialAccessTokenRes {

    private KakaoAccessTokenRes(String accessToken, String refreshToken) {
        super(accessToken, refreshToken);
    }
    public static KakaoAccessTokenRes of(String accessToken, String refreshToken) {
        return new KakaoAccessTokenRes(accessToken, refreshToken);
    }
}
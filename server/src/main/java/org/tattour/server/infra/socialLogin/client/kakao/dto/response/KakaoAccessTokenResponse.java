package org.tattour.server.infra.socialLogin.client.kakao.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoAccessTokenResponse {

    private String accessToken;
    private String refreshToken;

    public static KakaoAccessTokenResponse of(String accessToken, String refreshToken) {
        return new KakaoAccessTokenResponse(accessToken, refreshToken);
    }
}
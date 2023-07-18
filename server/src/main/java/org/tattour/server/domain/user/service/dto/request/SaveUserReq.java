package org.tattour.server.domain.user.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.infra.socialLogin.client.kakao.domain.SocialPlatform;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveUserReq {
    private Long kakaoId;
    private SocialPlatform socialPlatform;
    private String accessToken;
    private String refreshToken;

    public static SaveUserReq of(Long kakaoId, SocialPlatform socialPlatform, String accessToken, String refreshToken){
        return new SaveUserReq(kakaoId, socialPlatform, accessToken, refreshToken);
    }
}

package org.tattour.server.user.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.infra.socialLogin.client.kakao.domain.SocialPlatform;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveUserReq {
    private String email;
    private SocialPlatform socialPlatform;
    private String accessToken;
    private String refreshToken;

    public static SaveUserReq of(String email, SocialPlatform socialPlatform, String accessToken, String refreshToken){
        return new SaveUserReq(email, socialPlatform, accessToken, refreshToken);
    }
}

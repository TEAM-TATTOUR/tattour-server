package org.tattour.server.domain.user.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.infra.socialLogin.client.kakao.domain.SocialPlatform;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateLoginReq {
    private SocialPlatform socialPlatform;
    private String code;
    // TODO : prod에서는 수정해야함
    private String referer;

    public static CreateLoginReq of(SocialPlatform socialPlatform, String code, String referer) {
        return new CreateLoginReq(socialPlatform, code, referer);
    }
}

package org.tattour.server.domain.user.controller.dto.request;

import lombok.*;
import org.tattour.server.infra.socialLogin.client.kakao.domain.SocialPlatform;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginReq {

    private SocialPlatform socialPlatform;

    public static LoginReq of(SocialPlatform socialPlatform) {
        return new LoginReq(socialPlatform);
    }
}

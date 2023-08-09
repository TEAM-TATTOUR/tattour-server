package org.tattour.server.infra.socialLogin.client.kakao.service.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.infra.socialLogin.client.kakao.dto.response.SocialAccessTokenRes;
import org.tattour.server.infra.socialLogin.client.kakao.dto.response.SocialUserInfoRes;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SocialLoginInfo {
    private SocialAccessTokenRes socialAccessTokenRes;
    private SocialUserInfoRes socialUserInfoRes;
}

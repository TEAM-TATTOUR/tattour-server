package org.tattour.server.infra.socialLogin.client.kakao.service.vo;

import lombok.Getter;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.response.KakaoAccessTokenRes;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.response.KakaoUserInfoRes;

@Getter
public class KakaoLoginInfo extends SocialLoginInfo {
    private KakaoLoginInfo(KakaoAccessTokenRes kakaoAccessTokenRes, KakaoUserInfoRes kakaoUserInfoRes){
        super(kakaoAccessTokenRes, kakaoUserInfoRes);
    }
    public static KakaoLoginInfo of (KakaoAccessTokenRes kakaoAccessTokenRes, KakaoUserInfoRes kakaoUserInfoRes){
        return new KakaoLoginInfo(kakaoAccessTokenRes, kakaoUserInfoRes);
    }
}

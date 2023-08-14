package org.tattour.server.infra.socialLogin.client.kakao.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tattour.server.infra.socialLogin.client.kakao.KakaoApiClient;
import org.tattour.server.infra.socialLogin.client.kakao.KakaoAuthApiClient;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.response.KakaoAccessTokenRes;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.response.KakaoUserInfoRes;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialService;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.request.GetSocialLoginReq;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.infra.socialLogin.client.kakao.service.vo.KakaoLoginInfo;

@Service
@RequiredArgsConstructor
public class KakaoSocialService extends SocialService {

    @Value("${OAuth.kakao.clientId}")
    private String clientId;

    private final KakaoApiClient kakaoApiClient;
    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final UserServiceImpl userService;
    private final UserProviderImpl userProvider;

    @Override
    public KakaoLoginInfo getSocialLoginResponse(GetSocialLoginReq req) {
        // TODO : prod에서는 수정해야함
        // 요청자의 redirect url로 변경
        String redirectUrl = req.getOrigin() + "/login/oauth2/callback";

        // Authorization code로 Access Token 불러오기
        KakaoAccessTokenRes tokenResponse = kakaoAuthApiClient.getOAuth2AccessToken(
                "authorization_code",
                clientId,
                redirectUrl,
//                "https://tattour.kr/login/oauth2/callback",
//                "http://localhost:5173/login/oauth2/callback",
                req.getCode()
        );

        // Access Token으로 유저 정보 불러오기
        KakaoUserInfoRes kakaoUserInfoRes =
                kakaoApiClient.getUserInformation("Bearer " + tokenResponse.getAccessToken());

        return KakaoLoginInfo.of(tokenResponse, kakaoUserInfoRes);
    }
}
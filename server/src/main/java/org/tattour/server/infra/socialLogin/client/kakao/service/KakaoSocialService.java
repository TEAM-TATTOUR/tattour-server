package org.tattour.server.infra.socialLogin.client.kakao.service;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tattour.server.infra.socialLogin.client.kakao.KakaoApiClient;
import org.tattour.server.infra.socialLogin.client.kakao.KakaoAuthApiClient;
import org.tattour.server.infra.socialLogin.client.kakao.domain.SocialPlatform;
import org.tattour.server.infra.socialLogin.client.kakao.dto.response.KakaoAccessTokenResponse;
import org.tattour.server.infra.socialLogin.client.kakao.dto.response.KakaoUserRes;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.SocialLoginRequest;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.UserServiceImpl;
import org.tattour.server.domain.user.service.dto.request.SaveUserReq;

@Service
@RequiredArgsConstructor
public class KakaoSocialService extends SocialService{
    @Value("${OAuth.kakao.clientId}")
    private String clientId;

    private final KakaoApiClient kakaoApiClient;
    private final KakaoAuthApiClient kakaoAuthApiClient;
    private final UserServiceImpl userService;
    private final UserProviderImpl userProvider;

    @Override
    public Integer login(SocialLoginRequest request) {
        System.out.println(clientId);

        // Authorization code로 Access Token 불러오기
        KakaoAccessTokenResponse tokenResponse = kakaoAuthApiClient.getOAuth2AccessToken(
                "authorization_code",
                clientId,
                "http://localhost:8080/kakao/callback",
                request.getCode()
        );

        // Access Token으로 유저 정보 불러오기
        KakaoUserRes userResponse = kakaoApiClient.getUserInformation("Bearer " + tokenResponse.getAccessToken());
        Integer userId = userProvider.checkDuplicateByEmail(userResponse.getKakaoAccount().getEmail());

        // 존재하지 않으면 유저 생성
        if(Objects.isNull(userId)){
            User user = userService.saveUser(
                    SaveUserReq.of(
                            userResponse.getKakaoAccount().getEmail(),
                            SocialPlatform.KAKAO,
                            tokenResponse.getAccessToken(),
                            tokenResponse.getRefreshToken()));
            userId = user.getId();
        }

        return userId;
    }
}
package org.tattour.server.infra.socialLogin.client.kakao.service.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tattour.server.infra.socialLogin.client.kakao.KakaoApiClient;
import org.tattour.server.infra.socialLogin.client.kakao.KakaoAuthApiClient;
import org.tattour.server.infra.socialLogin.client.kakao.domain.SocialPlatform;
import org.tattour.server.infra.socialLogin.client.kakao.dto.response.KakaoAccessTokenResponse;
import org.tattour.server.infra.socialLogin.client.kakao.dto.response.KakaoUserRes;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialService;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.request.SocialLoginRequest;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.domain.user.service.dto.request.SaveUserReq;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.response.SocialLoginResponse;

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
    public SocialLoginResponse login(SocialLoginRequest req) {
        System.out.println(clientId);

        // Authorization code로 Access Token 불러오기
        KakaoAccessTokenResponse tokenResponse = kakaoAuthApiClient.getOAuth2AccessToken(
                "authorization_code",
                clientId,
                "https://tattour.kr/kakao/callback",
                // "http://localhost:5173/login/oauth2/callback",
               // "http://localhost:8080/kakao/callback",
                req.getCode()
        );

        // Access Token으로 유저 정보 불러오기
        KakaoUserRes userResponse = kakaoApiClient.getUserInformation(
                "Bearer " + tokenResponse.getAccessToken());

        // 중복 확인
        Integer userId = userProvider.checkDuplicationByKakaoId(userResponse.getId());
        Boolean isUserExist = false;

        // 존재하지 않으면 유저 생성
        if (Objects.isNull(userId)) {
            User user = userService.saveSocialUser(
                    SaveUserReq.of(
                            userResponse.getId(),
                            SocialPlatform.KAKAO,
                            tokenResponse.getAccessToken(),
                            tokenResponse.getRefreshToken()));
            userId = user.getId();
        } else {
            User user = userProvider.getUserById(userId);

            user.setSocialToken(tokenResponse.getAccessToken(), tokenResponse.getRefreshToken());
            userService.saveUser(user);

            if (!Objects.isNull(user.getName())) {
                isUserExist = true;
            }
        }

        return SocialLoginResponse.of(userId, isUserExist);
    }
}

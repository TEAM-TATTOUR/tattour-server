package org.tattour.server.infra.socialLogin.client.kakao.service;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import org.tattour.server.infra.socialLogin.client.kakao.domain.SocialPlatform;
import org.tattour.server.infra.socialLogin.client.kakao.service.impl.KakaoSocialService;

@Component
@RequiredArgsConstructor
public class SocialServiceProvider {

    private static final Map<SocialPlatform, SocialService> socialServiceMap = new HashMap<>();

    private final KakaoSocialService kakaoSocialService;

    @PostConstruct
    void initializeSocialServiceMap() {
        socialServiceMap.put(SocialPlatform.KAKAO, kakaoSocialService);
    }

    public SocialService getSocialService(SocialPlatform socialPlatform) {
        return socialServiceMap.get(socialPlatform);
    }
}
package org.tattour.server.infra.socialLogin.client.kakao.service;

import org.tattour.server.infra.socialLogin.client.kakao.service.dto.request.SocialLoginRequest;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.response.SocialLoginResponse;

public abstract class SocialService {

    public abstract SocialLoginResponse login(SocialLoginRequest request);
}
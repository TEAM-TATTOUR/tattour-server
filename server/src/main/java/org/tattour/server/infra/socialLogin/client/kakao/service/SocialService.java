package org.tattour.server.infra.socialLogin.client.kakao.service;

import org.tattour.server.infra.socialLogin.client.kakao.service.dto.SocialLoginRequest;

public abstract class SocialService {
    public abstract Integer login(SocialLoginRequest req);
}
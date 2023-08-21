package org.tattour.server.infra.socialLogin.client.kakao.service;

import org.tattour.server.infra.socialLogin.client.kakao.service.dto.request.GetSocialLoginReq;
import org.tattour.server.infra.socialLogin.client.kakao.service.vo.SocialLoginInfo;

public abstract class SocialService {

    public abstract SocialLoginInfo getSocialLoginResponse(GetSocialLoginReq request);
}
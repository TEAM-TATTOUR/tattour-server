package org.tattour.server.infra.socialLogin.client.kakao.service.dto.request.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KakaoUserInfoRes extends SocialUserInfoRes {

    //    private KakaoAccount kakaoAccount;
    private KakaoUserInfoRes(Long id) {
        super(id);
    }
    public static KakaoUserInfoRes of(Long id) {
        return new KakaoUserInfoRes(id);
    }
}
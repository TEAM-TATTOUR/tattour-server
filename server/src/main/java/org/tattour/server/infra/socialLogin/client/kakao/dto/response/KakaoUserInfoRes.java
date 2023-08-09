package org.tattour.server.infra.socialLogin.client.kakao.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.tattour.server.infra.socialLogin.client.kakao.service.vo.SocialLoginInfo;

@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class KakaoUserInfoRes extends SocialUserInfoRes {
//    private KakaoAccount kakaoAccount;
    private KakaoUserInfoRes(Long id) {
        super(id);
    }
    public static KakaoUserInfoRes of(Long id) {
        return new KakaoUserInfoRes(id);
    }
}
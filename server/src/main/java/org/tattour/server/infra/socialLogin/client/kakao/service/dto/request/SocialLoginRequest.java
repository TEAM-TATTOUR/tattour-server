package org.tattour.server.infra.socialLogin.client.kakao.service.dto.request;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SocialLoginRequest {

    private String code;
    private String redirectUri;


    public static SocialLoginRequest of(String code, String redirectUri) {
        return new SocialLoginRequest(code, redirectUri);
    }
}

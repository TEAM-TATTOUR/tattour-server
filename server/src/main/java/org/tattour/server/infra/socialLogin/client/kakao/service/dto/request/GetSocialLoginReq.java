package org.tattour.server.infra.socialLogin.client.kakao.service.dto.request;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetSocialLoginReq {

    private String code;

    public static GetSocialLoginReq of(String code) {
        return new GetSocialLoginReq(code);
    }
}

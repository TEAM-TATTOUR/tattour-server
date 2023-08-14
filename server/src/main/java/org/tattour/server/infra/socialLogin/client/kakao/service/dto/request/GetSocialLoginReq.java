package org.tattour.server.infra.socialLogin.client.kakao.service.dto.request;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetSocialLoginReq {

    private String code;
    // TODO : prod에서는 수정해야함
    private String host;

    public static GetSocialLoginReq of(String code, String host) {
        return new GetSocialLoginReq(code, host);
    }
}

package org.tattour.server.infra.socialLogin.client.kakao;

import org.apache.http.HttpHeaders;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.tattour.server.infra.socialLogin.client.kakao.dto.response.KakaoUserRes;

@FeignClient(name = "kakaoApiClient", url = "https://kapi.kakao.com")
public interface KakaoApiClient {
    @GetMapping(value = "/v2/user/me")
    KakaoUserRes getUserInformation(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken);
}
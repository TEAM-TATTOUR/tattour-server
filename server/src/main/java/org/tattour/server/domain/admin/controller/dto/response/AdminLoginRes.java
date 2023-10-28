package org.tattour.server.domain.admin.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "관리자 로그인 Response")
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminLoginRes {

    @Schema(description = "JWT access token")
    private String accessToken;

    public static AdminLoginRes of(String accessToken) {
        return AdminLoginRes.builder()
                .accessToken(accessToken)
                .build();
    }
}

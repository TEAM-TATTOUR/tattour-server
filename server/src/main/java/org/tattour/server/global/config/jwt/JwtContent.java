package org.tattour.server.global.config.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtContent {

    private String userId;
    private String role;

    @Builder
    private JwtContent(Object userId, Object role) {
        this.userId = String.valueOf(userId);
        this.role = String.valueOf(role);
    }

    public static JwtContent of(Object userId, Object role) {
        return JwtContent.builder()
                .userId(userId)
                .role(role)
                .build();
    }
}

package org.tattour.server.domain.user.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserRole {
    ADMIN("관리자"),
    USER("일반유저")
    ;

    private final String value;
}

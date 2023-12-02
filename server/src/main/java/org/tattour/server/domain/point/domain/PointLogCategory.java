package org.tattour.server.domain.point.domain;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PointLogCategory {
    PURCHASE("상품 구매"),
    APPLY_CUSTOM("커스텀 신청"),
    CANCEL_PURCHASE("결제 취소"),
    REQUEST_CHARGE("포인트 충전 요청"),
    CANCEL_CHARGE("충전 취소"),
    ;

    private final String value;

    public static PointLogCategory fromValue(String value) {
        return Arrays.stream(PointLogCategory.values())
                .filter(category -> category.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "입력한 value와 일치하는 Enum 타입이 없습니다: " + value));
    }
}

package org.tattour.server.domain.point.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum PointLogCategory {
    PURCHASE("상품구매"),
    APPLY_CUSTOM("커스텀신청"),
    CANCEL_PURCHASE("결제취소"),
    REQUEST_CHARGE("포인트충전요청"),
    CANCEL_CHARGE("충전취소"),
    ;

    private final String value;
}

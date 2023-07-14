package org.tattour.server.domain.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum OrderStatus {
    PREPARATION("상품준비중"),
    CANCEL("주문취소"),
    ACCEPT("주문접수"),
    DELIVERING("배송중"),
    DELIVERED("배송완료"),
    ;

    private final String value;
}

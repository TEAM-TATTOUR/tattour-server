package org.tattour.server.domain.order.provider.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderHistoryPageInfo {

    private int page;
    private final int size = 10;
    private Long totalElements;
    private int totalPages;

    public OrderHistoryPageInfo(int page, Long totalElements, int totalPages) {
        this.page = page;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public static OrderHistoryPageInfo of(int page, Long totalElements, int totalPages) {
        return new OrderHistoryPageInfo(page, totalElements, totalPages);
    }
}

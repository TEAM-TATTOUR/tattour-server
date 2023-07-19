package org.tattour.server.domain.order.provider.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageInfoRes {

    private int page;
    private final int size = 10;
    private Long totalElements;
    private int totalPages;

    public PageInfoRes(int page, Long totalElements, int totalPages) {
        this.page = page;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public static PageInfoRes of(int page, Long totalElements, int totalPages) {
        return new PageInfoRes(page, totalElements, totalPages);
    }
}

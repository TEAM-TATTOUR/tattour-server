package org.tattour.server.domain.discount.service.dto.request;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.discount.domain.Discount;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class DiscountInfo {

    private String name;
    private Integer discountRate;
    private LocalDateTime startAt;
    private LocalDateTime endedAt;

    public static DiscountInfo of(Discount discount) {
        return DiscountInfo.builder()
                .name(discount.getName())
                .discountRate(builder().discountRate)
                .startAt(discount.getStartAt())
                .endedAt(discount.getExpiredAt())
                .build();
    }

    public static DiscountInfo from(String name, Integer discountRate, LocalDateTime startAt,
            LocalDateTime endedAt) {
        return DiscountInfo.builder()
                .name(name)
                .discountRate(discountRate)
                .startAt(startAt)
                .endedAt(endedAt)
                .build();
    }
}

package org.tattour.server.domain.discount.facade.dto.request;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.discount.domain.Discount;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class CreateDiscountReq {

	private String name;
	private Integer discountRate;
	private LocalDateTime startAt;
	private LocalDateTime endedAt;

	public static CreateDiscountReq of(
		String name,
		Integer discountRate,
		LocalDateTime startAt,
		LocalDateTime endedAt) {
		return CreateDiscountReq.builder()
			.name(name)
			.discountRate(discountRate)
			.startAt(startAt)
			.endedAt(endedAt)
			.build();
	}
}

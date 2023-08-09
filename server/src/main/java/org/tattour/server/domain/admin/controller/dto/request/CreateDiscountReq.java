package org.tattour.server.domain.admin.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
@NoArgsConstructor
public class CreateDiscountReq {

	@NotNull
	@Schema(description = "할인 정책 이름", example = "Open Event!")
	private String name;

	@Range(min = 0, max = 100)
	@Schema(description = "할인률 : 0~100")
	private Integer discountRate;

	@NotNull
	private LocalDateTime startAt;

	@NotNull
	private LocalDateTime endedAt;

	public org.tattour.server.domain.discount.facade.dto.request.CreateDiscountReq newDiscountInfo() {
		return org.tattour.server.domain.discount.facade.dto.request.CreateDiscountReq.of(name, discountRate, startAt, endedAt);
	}

}

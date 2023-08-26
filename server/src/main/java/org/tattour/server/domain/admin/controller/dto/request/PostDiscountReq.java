package org.tattour.server.domain.admin.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.tattour.server.domain.discount.facade.dto.request.CreateDiscountReq;

@Getter
@NoArgsConstructor
public class PostDiscountReq {

	@NotNull(message = "name은 필수입니다.")
	@Schema(description = "할인 정책 이름", example = "Open Event!")
	private String name;

	@Range(min = 0, max = 100, message = "discountRate은 0~100 사이여야 합니다.")
	@Schema(description = "할인률 : 0~100")
	private Integer discountRate;

	@NotNull(message = "startAt은 필수입니다.")
	private LocalDateTime startAt;

	@NotNull(message = "endedAt은 필수입니다.")
	private LocalDateTime endedAt;

	public CreateDiscountReq newCreateDiscountReq() {
		return CreateDiscountReq.of(name, discountRate, startAt, endedAt);
	}

}

package org.tattour.server.domain.admin.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.tattour.server.domain.discount.service.dto.request.DiscountInfo;

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

    public DiscountInfo newDiscountInfo() {
        return DiscountInfo.from(name, discountRate, startAt, endedAt);
    }

}

package org.tattour.server.domain.admin.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplyStickerDiscountReq {

	@NotNull
	@Schema(description = "할인 적용할 sticker Id")
	private Integer stickerId;

	@NotNull
	@Schema(description = "스티커에 적용할 할인 정책 id")
	private Integer discountId;
}

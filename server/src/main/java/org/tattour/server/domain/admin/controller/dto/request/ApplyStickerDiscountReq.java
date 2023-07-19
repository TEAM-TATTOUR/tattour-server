package org.tattour.server.domain.admin.controller.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplyStickerDiscountReq {

	@NotNull
	private Integer stickerId;

	@NotNull
	private Integer discountId;
}

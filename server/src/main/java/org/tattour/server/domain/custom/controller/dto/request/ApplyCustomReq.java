package org.tattour.server.domain.custom.controller.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplyCustomReq {

	private Boolean haveDesign;
}

package org.tattour.server.domain.custom.controller.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Setter
public class CreateCustomReq {

	private Boolean haveDesign;
	private Integer pageCount;
	private Integer size;


}

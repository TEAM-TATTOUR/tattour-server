package org.tattour.server.golbal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FailResponse {

	private int code;
	private String message;
}

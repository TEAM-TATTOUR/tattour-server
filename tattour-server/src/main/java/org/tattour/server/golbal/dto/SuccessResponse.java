package org.tattour.server.golbal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {

	private final int code;
	private final String message;
	private T data;
}

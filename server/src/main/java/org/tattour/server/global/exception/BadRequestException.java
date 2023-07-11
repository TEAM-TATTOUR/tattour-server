package org.tattour.server.global.exception;

public class BadRequestException extends BusinessException {

	public BadRequestException(ErrorType errorType) {
		super(errorType);
	}

	public BadRequestException() {
		super(ErrorType.VALIDATION_INPUT_EXCEPTION);
	}
}

package org.tattour.server.golbal.exception;

public class UnauthorizedException extends BusinessException {

	public UnauthorizedException(ErrorType errorType) {
		super(errorType);
	}

	public UnauthorizedException() {
		super(ErrorType.VALIDATION_UNAUTHORIZED_EXCEPTION);
	}
}
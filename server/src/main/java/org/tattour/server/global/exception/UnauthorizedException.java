package org.tattour.server.global.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class UnauthorizedException extends BusinessException {

	public UnauthorizedException(ErrorType errorType) {
		super(errorType);
	}

	public UnauthorizedException() {
		super(ErrorType.VALIDATION_UNAUTHORIZED_EXCEPTION);
	}
}
package org.tattour.server.domain.custom.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class InvalidCustomCompletedException extends BusinessException {

	public InvalidCustomCompletedException() {
		super(ErrorType.INVALID_PASSWORD_EXCEPTION);
	}
}

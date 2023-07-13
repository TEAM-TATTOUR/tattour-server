package org.tattour.server.domain.custom.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class NotFoundCustomException extends BusinessException {

	public NotFoundCustomException() {
		super(ErrorType.NOT_FOUND_USER_EXCEPTION);
	}
}

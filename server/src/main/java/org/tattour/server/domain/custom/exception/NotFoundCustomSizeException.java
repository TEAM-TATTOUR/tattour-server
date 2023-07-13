package org.tattour.server.domain.custom.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class NotFoundCustomSizeException extends BusinessException {

	public NotFoundCustomSizeException() {
		super(ErrorType.NOT_FOUND_CUSTOM_SIZE_EXCEPTION);
	}
}

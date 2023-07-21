package org.tattour.server.domain.custom.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class InvalidCustomImageCount extends BusinessException {

	public InvalidCustomImageCount(ErrorType errorType) {
		super(ErrorType.INVALID_IMAGE_EXCEPTION);
	}
}

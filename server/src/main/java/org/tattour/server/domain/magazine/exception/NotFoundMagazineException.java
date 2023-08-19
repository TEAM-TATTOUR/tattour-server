package org.tattour.server.domain.magazine.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class NotFoundMagazineException extends BusinessException {

	public NotFoundMagazineException() {
		super(ErrorType.NOT_FOUND_MAGAZINE_EXCEPTION);
	}
}

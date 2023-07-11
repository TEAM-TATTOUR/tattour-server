package org.tattour.server.theme.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class NotFoundThemeException extends BusinessException {

	public NotFoundThemeException() {
		super(ErrorType.NOT_FOUND_THEME_EXCEPTION);
	}
}

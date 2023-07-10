package org.tattour.server.user.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class NotFoundUserException extends BusinessException {
    public NotFoundUserException() {
        super(ErrorType.NOT_FOUND_USER_EXCEPTION);
    }
}

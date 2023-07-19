package org.tattour.server.domain.style.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class NotFoundStyleException extends BusinessException {

    public NotFoundStyleException() {
        super(ErrorType.NOT_FOUND_STYLE_EXCEPTION);
    }
}

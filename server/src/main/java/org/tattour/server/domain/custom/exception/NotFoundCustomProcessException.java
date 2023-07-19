package org.tattour.server.domain.custom.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class NotFoundCustomProcessException extends BusinessException {

    public NotFoundCustomProcessException() {
        super(ErrorType.NOT_FOUND_CUSTOM_PROCESS_EXCEPTION);
    }
}

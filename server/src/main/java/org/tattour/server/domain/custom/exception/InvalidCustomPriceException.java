package org.tattour.server.domain.custom.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class InvalidCustomPriceException extends BusinessException {

    public InvalidCustomPriceException() {
        super(ErrorType.INVALID_CUSTOM_COMPLETED_EXCEPTION);
    }
}

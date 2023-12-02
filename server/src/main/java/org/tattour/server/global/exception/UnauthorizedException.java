package org.tattour.server.global.exception;

public class UnauthorizedException extends BusinessException {

    public UnauthorizedException(ErrorType errorType) {
        super(errorType);
    }

    public UnauthorizedException() {
        super(ErrorType.UNAUTHORIZED_ACCESS_EXCEPTION);
    }
}
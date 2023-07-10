package org.tattour.server.global.exception;

public class BusinessException extends RuntimeException {

	private ErrorType errorType;

	public BusinessException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}

	public BusinessException(ErrorType errorType, String message) {
		super(message);
		this.errorType = errorType;
	}


	public ErrorType getErrorType() {
		return errorType;
	}

	public int getStatusCode() {
		return errorType.getStatusCode();
	}
}
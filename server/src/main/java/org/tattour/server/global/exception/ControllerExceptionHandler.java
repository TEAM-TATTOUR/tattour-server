package org.tattour.server.global.exception;

import java.util.Objects;
import javax.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.tattour.server.global.dto.ApiResponse;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler {

	/**
	 * 400 Error
	 * 잘못된 요청값
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		FieldError fieldError = Objects.requireNonNull(e.getFieldError());
		return ApiResponse.error(ErrorType.VALIDATION_INPUT_EXCEPTION,
			String.format("%s. (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
	}

	/**
	 * 400 Error
	 * 잘못된 타입 에러
	 */
	@ExceptionHandler(BindException.class)
	public ResponseEntity<?> handleBadRequest(BindException e) {
		FieldError fieldError = Objects.requireNonNull(e.getFieldError());
		return ApiResponse.error(ErrorType.VALIDATION_WRONG_TYPE_EXCEPTION,
			String.format("%s (%s)", fieldError.getDefaultMessage(), fieldError.getField()));
	}

	/**
	 * 400 Error
	 * 잘못된 요청 에러
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleDateTimeFormatException1(HttpMessageNotReadableException e) {
		return ApiResponse.error(ErrorType.VALIDATION_INPUT_EXCEPTION);
	}

	/**
	 * 400 Error
	 * Pageable에 허용하지 않는 정렬기준이 입력된 경우
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {
		return ApiResponse.error(ErrorType.VALIDATION_INPUT_EXCEPTION);
	}

	/**
	 * 400 Error
	 * 잘못된 HTTP Method
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<?> handleDateTimeFormatException2(HttpRequestMethodNotSupportedException e) {
		return ApiResponse.error(ErrorType.VALIDATION_WRONG_HTTP_METHOD_EXCEPTION);
	}

	// 나중에 수정하기
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentExceptionAdvice(IllegalArgumentException e) {
		return ApiResponse.error(ErrorType.INVALID_ARGUMENT_EXCEPTION);
	}
	/**
	 * 500 Error
	 * Internal Server Error
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<?> handleInternalServerException(Exception e) {
		log.info("e ={}", e);
		e.printStackTrace();
		return ApiResponse.error(ErrorType.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Custom Error
	 */
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<?> handleBusinessException(BusinessException e) {
		return ApiResponse.error(e.getErrorType());
	}
}
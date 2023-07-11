package org.tattour.server.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorType {

	/**
	 * 400 BAD REQUEST
	 */
	VALIDATION_INPUT_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),
	VALIDATION_WRONG_TYPE_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 타입이 입력되었습니다"),
	INVALID_PASSWORD_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다."),
	INVALID_ARGUMENT_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 인자가 입력되었습니다."),

	/**
	 * 401 UNAUTHORIZED
	 */
	VALIDATION_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED, "리소스에 접근 권한이 없습니다."),
	TOKEN_TIME_EXPIRED_EXCEPTION(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
	TOKEN_USERID_PATH_USERID_MISMATCH_EXCEPTION(HttpStatus.UNAUTHORIZED, "유저 정보가 일치하지 않습니다."),

	/**
	 * 404 NOT FOUND
	 */
	NOT_FOUND_RESOURCE(HttpStatus.NOT_FOUND, "존재하지 않는 데이터입니다."),
	NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다"),
	NOT_FOUND_STICKER_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 스티커입니다"),
	NOT_FOUND_VERIFICATION_CODE_EXCEPTION(HttpStatus.NOT_FOUND, "인증번호가 존재하지 않습니다."),

	/**
	 * 405 METHOD NOT ALLOWED
	 */
	VALIDATION_WRONG_HTTP_METHOD_EXCEPTION(HttpStatus.METHOD_NOT_ALLOWED, "잘못된 HTTP 메서드 요청입니다."),

	/**
	 * 409 CONFLICT
	 */
	ALREADY_EXIST_USER_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 유저 이름입니다"),

	/**
	 * 500 INTERNAL SERVER ERROR
	 */
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 서버 에러가 발생했습니다"),
	;


	private final HttpStatus httpStatus;
	private final String message;

	public int getStatusCode() {
		return httpStatus.value();
	}

	public String getMessage() {
		return message;
	}
}
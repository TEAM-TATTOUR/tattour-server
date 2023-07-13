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
	INVALID_IMAGE_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 이미지 파일입니다."),
	INVALID_MULTIPART_EXTENSION_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 파일 확장자입니다."),
	INVALID_CUSTOM_PRICE_EXCEPTION(HttpStatus.BAD_REQUEST, "잘못된 커스텀 가격 입니다."),

	/**
	 * 401 UNAUTHORIZED
	 */
	VALIDATION_UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED, "리소스에 접근 권한이 없습니다."),
	TOKEN_TIME_EXPIRED_EXCEPTION(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다."),
	TOKEN_USERID_PATH_USERID_MISMATCH_EXCEPTION(HttpStatus.UNAUTHORIZED, "유저 정보가 일치하지 않습니다."),

	/**
	 * 403 FORBIDDEN
	 */
	LACK_OF_POINT_EXCEPTION(HttpStatus.FORBIDDEN, "포인트가 부족하여 결제할 수 없습니다."),

	/**
	 * 404 NOT FOUND
	 */
	NOT_FOUND_RESOURCE(HttpStatus.NOT_FOUND, "존재하지 않는 데이터입니다."),
	NOT_FOUND_USER_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다"),
	NOT_FOUND_STICKER_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 스티커입니다"),
	NOT_FOUND_CUSTOM_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 커스텀입니다"),
	NOT_FOUND_CUSTOM_PROCESS_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 커스텀상태입니다"),
	NOT_FOUND_CUSTOM_SIZE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 커스텀 크기입니다"),
	NOT_FOUND_THEME_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 테마입니다"),
	NOT_FOUND_STYLE_EXCEPTION(HttpStatus.NOT_FOUND, "존재하지 않는 스타일입니다"),
	NOT_FOUND_VERIFICATION_CODE_EXCEPTION(HttpStatus.NOT_FOUND, "인증번호가 존재하지 않습니다."),
	NOT_FOUND_SAVE_IMAGE_EXCEPTION(HttpStatus.NOT_FOUND, "이미지가 존재하지 않습니다"),
	NOT_FOUND_STICKER_SORT_EXCEPTION(HttpStatus.NOT_FOUND, "스티커 정렬 기준이 존재하지 않습니다"),

	/**
	 * 405 METHOD NOT ALLOWED
	 */
	VALIDATION_WRONG_HTTP_METHOD_EXCEPTION(HttpStatus.METHOD_NOT_ALLOWED, "잘못된 HTTP 메서드 요청입니다."),

	/**
	 * 409 CONFLICT
	 */
	ALREADY_EXIST_USER_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 유저 이름입니다"),
	ALREADY_EXIST_PRODUCTLIKED_EXCEPTION(HttpStatus.CONFLICT, "이미 존재하는 좋아요한 상품입니다"),

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
package org.tattour.server.golbal.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessType {

	/**
	 * 200 OK
	 */
	LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
	READ_ALL_STICKER_SUCCESS(HttpStatus.OK, "모든 스티커 요약 정보 조회에 성공했습니다."),
	READ_HOT_CUSTOM_STICKER_SUCCESS(HttpStatus.OK, "인기 스티커 요약 정보 조회에 성공했습니다."),
	READ_STICKER_INFO_SUCCESS(HttpStatus.OK, "한 게시물 상세 정보 조회에 성공했습니다."),

	/**
	 * 201 CREATED
	 */
	SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
	CREATE_BOARD_SUCCESS(HttpStatus.CREATED, "게시물 생성이 완료됐습니다."),
	CREATE_EMOTION_SUCCESS(HttpStatus.CREATED, "감정 기록에 성공했습니다."),
	;


	private final HttpStatus httpStatus;
	private final String message;

	public int getHttpStatusCode() {
		return httpStatus.value();
	}

	public String getMessage() {
		return message;
	}
}
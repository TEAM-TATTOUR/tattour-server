package org.tattour.server.global.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessType {

	/**
	 * 200 OK
	 */
	GET_SUCCESS(HttpStatus.OK, "조회에 성공했습니다."),
	UPDATE_SUCCESS(HttpStatus.OK, "갱신에 성공했습니다."),
	DELETE_SUSCCESS(HttpStatus.OK, "삭제에 성공했습니다."),
	LOGIN_SUCCESS(HttpStatus.OK, "로그인에 성공했습니다."),
	LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃에 성공했습니다."),
	READ_ALL_STICKER_SUCCESS(HttpStatus.OK, "모든 스티커 요약 정보 조회에 성공했습니다."),
	READ_HOT_CUSTOM_STICKER_SUCCESS(HttpStatus.OK, "인기 스티커 요약 정보 조회에 성공했습니다."),
	READ_STICKER_INFO_SUCCESS(HttpStatus.OK, "한 게시물 상세 정보 조회에 성공했습니다."),
	CODE_VERIFICATION_SUCCESS(HttpStatus.OK, "인증코드 검증에 성공했습니다."),
	READ_SIMILAR_STICKER_SUCCESS(HttpStatus.OK, "비슷한 스티커 리스트 조회에 성공했습니다"),
	READ_FILTER_ALL_STICKER_SUCCESS(HttpStatus.OK, "스티커 필터링 조회에 성공했습니다."),
	READ_SEARCH_ALL_STICKER_SUCCESS(HttpStatus.OK, "스티커 검색 조회에 성공했습니다."),

	/**
	 * 201 CREATED
	 */
	CREATE_SUCCESS(HttpStatus.CREATED, "생성에 성공하였습니다."),
	SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 완료됐습니다."),
	CREATE_CUSTOM_SUCCESS(HttpStatus.CREATED, "커스텀 도안 신청이 완료됐습니다."),
	CREATE_ORDER_SUCCESS(HttpStatus.CREATED, "주문에 성공했습니다."),
	CREATE_POINT_CHARGE_REQUEST_SUCCESS(HttpStatus.CREATED, "포인트 충전 요청에 성공했습니다."),

	/**
	 * 202 ACCEPTED
	 */
	CODE_VALIDATION_FAIL(HttpStatus.ACCEPTED, "인증번호 검증에 실패했습니다."),

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
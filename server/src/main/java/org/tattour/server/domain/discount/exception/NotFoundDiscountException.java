package org.tattour.server.domain.discount.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class NotFoundDiscountException extends BusinessException {

	public NotFoundDiscountException() {
		super(ErrorType.NOT_FOUND_RESOURCE, "존재하지 않은 할인 정책입니다.");
	}
}

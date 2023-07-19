package org.tattour.server.domain.sticker.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class NotFoundStickerException extends BusinessException {

	public NotFoundStickerException() {
		super(ErrorType.NOT_FOUND_STICKER_EXCEPTION);
	}
}

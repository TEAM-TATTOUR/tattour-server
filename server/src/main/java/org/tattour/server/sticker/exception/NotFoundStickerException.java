package org.tattour.server.sticker.exception;

import org.tattour.server.golbal.exception.BusinessException;
import org.tattour.server.golbal.exception.ErrorType;

public class NotFoundStickerException extends BusinessException {

	public NotFoundStickerException() {
		super(ErrorType.NOT_FOUND_STICKER_EXCEPTION);
	}
}

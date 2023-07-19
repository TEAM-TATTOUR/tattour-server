package org.tattour.server.domain.sticker.exception;

import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

public class NotFoundStickerSortException extends BusinessException {

    public NotFoundStickerSortException() {
        super(ErrorType.NOT_FOUND_STICKER_SORT_EXCEPTION);
    }
}

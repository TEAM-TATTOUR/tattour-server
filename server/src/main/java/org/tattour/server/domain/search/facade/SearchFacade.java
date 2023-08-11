package org.tattour.server.domain.search.facade;

import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerSummaryListRes;

public interface SearchFacade {

    ReadStickerSummaryListRes searchSticker(String word);
}

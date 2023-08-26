package org.tattour.server.domain.sticker.facade;

import org.tattour.server.domain.sticker.facade.dto.request.CreateStickerReq;
import org.tattour.server.domain.sticker.facade.dto.response.ReadOrderSheetStickerRes;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerForUserRes;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerSummaryListRes;

public interface StickerFacade {

    Integer createSticker(CreateStickerReq request);

    ReadStickerSummaryListRes readHotCustomStickerList();

    ReadStickerForUserRes readStickerForUser(Integer stickerId, String authorization);

    ReadStickerSummaryListRes readSimilarStickerSummaryList(Integer stickerId);

    ReadStickerSummaryListRes readFilterStickerSummaryList(String sort, String theme, String style);
}

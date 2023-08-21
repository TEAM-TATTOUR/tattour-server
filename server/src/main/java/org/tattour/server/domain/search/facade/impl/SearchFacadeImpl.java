package org.tattour.server.domain.search.facade.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.search.facade.SearchFacade;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerSummaryListRes;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchFacadeImpl implements SearchFacade {

    private final StickerProvider stickerProvider;

    @Override
    @Transactional(readOnly = true)
    public ReadStickerSummaryListRes searchSticker(String word) {
        List<Sticker> result =
                stickerProvider.getAllByThemeOrStyleOrNameLike(word);
        return ReadStickerSummaryListRes.from(result);
    }
}

package org.tattour.server.domain.search.facade.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.search.facade.SearchFacade;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.StickerProvider;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerSummaryListRes;
import org.tattour.server.domain.sticker.service.StickerService;
import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.style.provider.StyleProvider;
import org.tattour.server.domain.theme.domain.Theme;
import org.tattour.server.domain.theme.provider.ThemeProvider;

@Service
@RequiredArgsConstructor
public class SearchFacadeImpl implements SearchFacade {

    private final StickerService stickerService;
    private final StickerProvider stickerProvider;
    private final ThemeProvider themeProvider;
    private final StyleProvider styleProvider;

    @Override
    @Transactional(readOnly = true)
    public ReadStickerSummaryListRes searchSticker(String word) {
        List<Sticker> result = stickerProvider.getAllByNameLike(word);
        List<Theme> themes = themeProvider.getAllByNameLike(word);
        List<Style> styles = styleProvider.getAllByNameLike(word);
        stickerService.addStickerListByThemeList(result, themes);
        stickerService.addStickerListByStyleList(result, styles);
        return ReadStickerSummaryListRes.from(result);
    }
}

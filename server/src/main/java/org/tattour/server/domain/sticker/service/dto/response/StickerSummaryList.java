package org.tattour.server.domain.sticker.service.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.sticker.domain.Sticker;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StickerSummaryList {

    private List<StickerSummary> stickers;

    public static StickerSummaryList of(List<Sticker> stickers) {
        List<StickerSummary> stickerSummary = stickers
                .stream()
                .map(StickerSummary::of)
                .collect(Collectors.toList());
        return new StickerSummaryList(stickerSummary);
    }
}

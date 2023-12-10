package org.tattour.server.domain.sticker.facade.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.sticker.model.Sticker;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadStickerSummaryListRes {

    private List<ReadStickerSummaryRes> stickers;

    public static ReadStickerSummaryListRes from(List<Sticker> stickers) {
        List<ReadStickerSummaryRes> readStickerSummaryRes = stickers
                .stream()
                .map(ReadStickerSummaryRes::from)
                .collect(Collectors.toList());
        return new ReadStickerSummaryListRes(readStickerSummaryRes);
    }
}

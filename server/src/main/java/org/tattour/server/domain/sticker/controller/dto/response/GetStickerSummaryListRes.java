package org.tattour.server.domain.sticker.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.sticker.facade.dto.response.ReadStickerSummaryListRes;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetStickerSummaryListRes {

    @Schema(description = "스티커 요약 리스트")
    private List<GetStickerSummaryRes> stickers;

    public static GetStickerSummaryListRes from(ReadStickerSummaryListRes readStickerSummaryListRes) {
        return new GetStickerSummaryListRes(
            readStickerSummaryListRes
                .getStickers()
                .stream()
                .map(GetStickerSummaryRes::from)
                .collect(Collectors.toList()));
    }
}

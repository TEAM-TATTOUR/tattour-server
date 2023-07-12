package org.tattour.server.domain.sticker.service.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.tattour.server.domain.sticker.domain.Sticker;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StickerSummaryListRes {

	private List<StickerSummaryRes> stickers;

	public static StickerSummaryListRes of(List<Sticker> stickers) {
		List<StickerSummaryRes> stickerSummaryRes = stickers
			.stream()
			.map(StickerSummaryRes::of)
			.collect(Collectors.toList());
		return new StickerSummaryListRes(stickerSummaryRes);
	}
}

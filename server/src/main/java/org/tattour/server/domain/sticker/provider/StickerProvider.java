package org.tattour.server.domain.sticker.provider;

import java.util.List;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.provider.vo.ReadOrderSheetStickerInfo;

public interface StickerProvider {

    // 스티커 엔티티 가져오기
    Sticker getById(Integer id);

    List<Sticker> getAllCustomStickerOrderByOrder();

    List<Sticker> getAllByThemeAndStyleOrderByOrder(String theme, String style);

    List<Sticker> getAllByThemeAndStyleOrderByPrice(String theme, String style);

    List<Sticker> getAllByThemeAndStyleOrderByPriceDesc(String theme, String style);

    List<Sticker> getAllSameThemeOrStyleById(Integer id);

    List<Sticker> getAllByThemeOrStyleOrNameLike(String word);

    // 결제 시트에서 스티커 정보 가져오기
    ReadOrderSheetStickerInfo readOrderSheetStickerInfo(Sticker sticker);
}

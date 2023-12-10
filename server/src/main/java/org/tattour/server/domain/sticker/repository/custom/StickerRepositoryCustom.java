package org.tattour.server.domain.sticker.repository.custom;

import java.util.List;
import org.tattour.server.domain.sticker.model.Sticker;

public interface StickerRepositoryCustom {

	List<Sticker> findAllByStateAndIsCustomInOrderOrder();

	List<Sticker> findAllSameThemeOrStyleById(Integer id);

	List<Sticker> findAllByThemeNameAndStyleNameAndStateInOrderOrder(String themeName, String styleName);

	List<Sticker> findAllByThemeNameAndStyleNameAndStateInOrderPrice(String themeName, String styleName);

	List<Sticker> findAllByThemeNameAndStyleNameAndStateInOrderPriceDesc(String themeName, String styleName);

	List<Sticker> findAllByThemeNameOrStyleNameOrNameContaining(String word);
}

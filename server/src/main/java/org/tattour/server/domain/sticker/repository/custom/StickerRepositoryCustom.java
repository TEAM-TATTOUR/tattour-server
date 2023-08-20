package org.tattour.server.domain.sticker.repository.custom;

import java.util.List;
import org.springframework.data.repository.query.Param;
import org.tattour.server.domain.sticker.domain.Sticker;

public interface StickerRepositoryCustom {

	List<Sticker> findAllByStateAndIsCustomInOrderOrder();

	List<Sticker> findAllSameThemeOrStyleById(@Param("id") Integer id);
}

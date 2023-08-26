package org.tattour.server.domain.sticker.repository;

import java.util.Optional;
import org.springframework.data.repository.Repository;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.repository.custom.StickerRepositoryCustom;

public interface StickerRepository extends
	Repository<Sticker, Integer>,
		StickerRepositoryCustom {

	Sticker save(Sticker sticker);

	Optional<Sticker> findById(Integer id);
}

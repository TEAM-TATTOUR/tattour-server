package org.tattour.server.domain.sticker.repository;

import java.util.List;
import java.util.Optional;
import org.tattour.server.domain.sticker.domain.Sticker;

public interface StickerRepository {

	Sticker save(Sticker sticker);

	Optional<Sticker> findById(Integer id);

	List<Sticker> findAll();

	List<Sticker> findAllByIsCustomTrueAndStateTrue();

	List<Sticker> findAllByStateTrue();

	List<Sticker> findByNameContaining(String name);
}

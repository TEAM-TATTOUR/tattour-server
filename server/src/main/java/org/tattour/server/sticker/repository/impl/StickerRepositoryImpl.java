package org.tattour.server.sticker.repository.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.sticker.domain.Sticker;

@Repository
public interface StickerRepositoryImpl extends JpaRepository<Sticker, Long> {

	List<Sticker> findAllByIsCustomTrueAndStateTrue();
	List<Sticker> findAllByStateTrue();
}

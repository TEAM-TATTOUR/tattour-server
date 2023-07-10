package org.tattour.server.sticker.repository.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.sticker.domain.Sticker;

@Repository
public interface StickerRepositoryImpl extends JpaRepository<Sticker, Integer> {

	List<Sticker> findAllByIsCustomTrueAndStateTrue();
	List<Sticker> findAllByStateTrue();
	Optional<Sticker> findByIdAndStateTrue(Integer stickerId);
}

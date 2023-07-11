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

	List<Sticker> findByNameContaining(String name);

	List<Sticker> findAllByStateTrueOrderByPriceAsc();
	List<Sticker> findAllByStateTrueOrderByPriceDesc();

	List<Sticker> findAllByStickerThemes_IdAndStateTrue(Integer stickerThemesId);
	List<Sticker> findAllByStickerThemes_IdAndStateTrueOrderByPriceAsc(Integer stickerThemesId);
	List<Sticker> findAllByStickerThemes_IdAndStateTrueOrderByPriceDesc(Integer stickerThemesId);

	List<Sticker> findAllByStickerStyles_IdAndStateTrue(Integer stickerStyleId);
	List<Sticker> findAllByStickerStyles_IdAndStateTrueOrderByPriceAsc(Integer stickerStyleId);
	List<Sticker> findAllByStickerStyles_IdAndStateTrueOrderByPriceDesc(Integer stickerStyleId);

	List<Sticker> findAllByStickerThemes_IdAndStickerStyles_IdAndStateTrue(Integer stickerThemesId, Integer stickerStyleId);
	List<Sticker> findAllByStickerThemes_IdAndStickerStyles_IdAndStateTrueOrderByPriceAsc(Integer stickerThemesId, Integer stickerStyleId);
	List<Sticker> findAllByStickerThemes_IdAndStickerStyles_IdAndStateTrueOrderByPriceDesc(Integer stickerThemesId, Integer stickerStyleId);

}

package org.tattour.server.domain.sticker.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.repository.StickerRepository;

public interface StickerRepositoryImpl extends
	Repository<Sticker, Integer>,
	StickerRepository {

//    List<Sticker> findAllByStateTrueOrderByPriceAsc();
//
//    List<Sticker> findAllByStateTrueOrderByPriceDesc();
//
//    List<Sticker> findAllByStickerThemes_IdAndStateTrue(Integer stickerThemesId);
//
//    List<Sticker> findAllByStickerThemes_IdAndStateTrueOrderByPriceAsc(Integer stickerThemesId);
//
//    List<Sticker> findAllByStickerThemes_IdAndStateTrueOrderByPriceDesc(Integer stickerThemesId);
//
//    List<Sticker> findAllByStickerStyles_IdAndStateTrue(Integer stickerStyleId);
//
//    List<Sticker> findAllByStickerStyles_IdAndStateTrueOrderByPriceAsc(Integer stickerStyleId);
//
//    List<Sticker> findAllByStickerStyles_IdAndStateTrueOrderByPriceDesc(Integer stickerStyleId);
//
//    List<Sticker> findAllByStickerThemes_IdAndStickerStyles_IdAndStateTrue(Integer stickerThemesId,
//            Integer stickerStyleId);
//
//    List<Sticker> findAllByStickerThemes_IdAndStickerStyles_IdAndStateTrueOrderByPriceAsc(
//            Integer stickerThemesId, Integer stickerStyleId);
//
//    List<Sticker> findAllByStickerThemes_IdAndStickerStyles_IdAndStateTrueOrderByPriceDesc(
//            Integer stickerThemesId, Integer stickerStyleId);
}

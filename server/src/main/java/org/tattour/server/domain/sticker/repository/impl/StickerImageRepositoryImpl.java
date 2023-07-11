package org.tattour.server.domain.sticker.repository.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.sticker.domain.StickerImage;

@Repository
public interface StickerImageRepositoryImpl extends JpaRepository<StickerImage, Integer> {

	List<StickerImage> findAllByStickerId(Integer stickerId);
}

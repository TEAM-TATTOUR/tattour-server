package org.tattour.server.sticker.repository.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.sticker.domain.StickerImage;

@Repository
public interface StickerImageRepositoryImpl extends JpaRepository<StickerImage, Long> {

	List<StickerImage> findAllByStickerId(Long stickerId);
}

package org.tattour.server.domain.sticker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.domain.StickerImage;
import org.tattour.server.domain.sticker.repository.StickerImageRepository;
import org.tattour.server.domain.sticker.service.StickerImageService;

@Service
@RequiredArgsConstructor
public class StickerImageServiceImpl implements StickerImageService {

	private final StickerImageRepository stickerImageRepository;

	@Override
	public StickerImage save(StickerImage stickerImage) {
		return stickerImageRepository.save(stickerImage);
	}
}

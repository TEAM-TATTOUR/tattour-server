package org.tattour.server.domain.sticker.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.domain.StickerStyle;
import org.tattour.server.domain.sticker.repository.StickerStyleRepository;
import org.tattour.server.domain.sticker.service.StickerStyleService;

@Service
@RequiredArgsConstructor
public class StickerStyleServiceImpl implements StickerStyleService {

	private final StickerStyleRepository stickerStyleRepository;

	@Override
	public StickerStyle save(StickerStyle stickerStyle) {
		return stickerStyleRepository.save(stickerStyle);
	}
}

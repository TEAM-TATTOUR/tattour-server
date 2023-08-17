package org.tattour.server.domain.sticker.service.impl;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.repository.StickerRepository;
import org.tattour.server.domain.sticker.service.StickerService;

@Service
@RequiredArgsConstructor
public class StickerServiceImpl implements StickerService {

	private final StickerRepository stickerRepository;

	@Override
	public Sticker save(Sticker sticker) {
		return stickerRepository.save(sticker);
	}

	@Override
	public void sortStickerListByNumberOfOrderDesc(List<Sticker> stickers) {
		Collections.sort(stickers, (o1, o2) -> o2.getOrders().size() - o1.getOrders().size());
	}
}

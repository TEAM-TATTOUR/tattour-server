package org.tattour.server.domain.sticker.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.model.Sticker;
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
        stickers.sort((o1, o2) -> o2.getOrderedProducts().size() - o1.getOrderedProducts().size());
    }
}
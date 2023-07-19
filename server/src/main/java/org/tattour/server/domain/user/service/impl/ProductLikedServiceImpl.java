package org.tattour.server.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.user.provider.impl.ProductLikedProviderImpl;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.repository.impl.ProductLikedRepositoryImpl;
import org.tattour.server.domain.user.provider.dto.request.SaveProductLikedReq;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.sticker.service.StickerServiceImpl;
import org.tattour.server.domain.user.domain.ProductLiked;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.service.ProductLikedService;
import org.tattour.server.domain.user.service.dto.request.DeleteProductLikedInfo;

@Service
@RequiredArgsConstructor
public class ProductLikedServiceImpl implements ProductLikedService {

    private final ProductLikedRepositoryImpl productLikedRepository;
    private final ProductLikedProviderImpl productLikedProvider;
    private final UserServiceImpl userService;
    private final UserProviderImpl userProvider;
    private final StickerServiceImpl stickerService;

    @Override
    public void saveProductLiked(SaveProductLikedReq req) {
        User user = userProvider.getUserById(req.getUserId());
        Sticker sticker = stickerService.getStickerByStickerId(req.getStickerId());

        productLikedRepository.save(ProductLiked.of(user, sticker));
    }

    @Override
    public void deleteProductLiked(DeleteProductLikedInfo req) {
        ProductLiked productLiked = productLikedProvider.getProductLikedByIdAndUserId(
                req.getStickerId(), req.getUserId());

        productLikedRepository.delete(productLiked);
    }
}

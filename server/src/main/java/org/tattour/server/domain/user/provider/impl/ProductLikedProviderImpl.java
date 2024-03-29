package org.tattour.server.domain.user.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.user.model.ProductLiked;
import org.tattour.server.domain.user.provider.ProductLikedProvider;
import org.tattour.server.domain.user.repository.impl.ProductLikedRepositoryImpl;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@Service
@RequiredArgsConstructor
public class ProductLikedProviderImpl implements ProductLikedProvider {

    private final ProductLikedRepositoryImpl productLikedRepository;

    @Override
    public ProductLiked readProductLikedById(Integer id) {
        return productLikedRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorType.NOT_FOUND_RESOURCE));
    }

    @Override
    public ProductLiked readProductLikedByUserIdAndStickerId(Integer userId, Integer stickerId) {
        return productLikedRepository.findProductLikedByUser_IdAndSticker_Id(userId, stickerId)
                .orElseThrow(() -> new BusinessException(ErrorType.NOT_FOUND_RESOURCE));
    }

    @Override
    public List<ProductLiked> readLikedProductsByUserId(Integer userId) {
        return productLikedRepository.findAllByUser_IdOrderByCreatedAtDesc(userId);
    }

    @Override
    public Boolean checkDuplicationByStickerId(int userId, int stickerId) {
        return productLikedRepository.findProductLikedByUser_IdAndSticker_Id(userId, stickerId)
                .isPresent();
    }
}

package org.tattour.server.domain.user.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.user.domain.ProductLiked;
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

    //TODO : 리팩토링?
    @Override
    public List<ProductLiked> readLikedProductsByUserId(Integer userId) {
        return productLikedRepository.findAllByUser_IdOrderByCreatedAtDesc(userId);
        // 모아서 리스트로 만들기
//        List<Sticker> stickerList = productLikedList.stream()
//                .map(ProductLiked::getSticker)
//                .collect(Collectors.toList());
    }

    @Override
    public Boolean checkDuplicationByStickerId(int userId, int stickerId) {
        return productLikedRepository.findProductLikedByUser_IdAndSticker_Id(userId, stickerId)
                .isPresent();
    }
}

package org.tattour.server.domain.user.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.user.domain.ProductLiked;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.ProductLikedProvider;
import org.tattour.server.domain.user.provider.dto.response.ProductLikedListRes;
import org.tattour.server.domain.user.provider.dto.response.ProductLikedRes;
import org.tattour.server.domain.user.repository.impl.ProductLikedRepositoryImpl;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.util.EntityDtoMapper;

@Service
@RequiredArgsConstructor
public class ProductLikedProviderImpl implements ProductLikedProvider {
    private final ProductLikedRepositoryImpl productLikedRepository;

    @Override
    public ProductLiked getProductLikedById(Integer id) {
        return productLikedRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorType.NOT_FOUND_RESOURCE));
    }

    @Override
    public ProductLiked getProductLikedByIdAndUserId(Integer id, Integer userId) {
        return productLikedRepository.findByIdAndUser_Id(id, userId)
                .orElseThrow(() -> new BusinessException(ErrorType.NOT_FOUND_RESOURCE));
    }

    @Override
    public ProductLikedListRes getLikedProductsByUserId(Integer userId) {
        List<ProductLiked> productLikedList = productLikedRepository.findAllByUser_Id(userId);
        List<ProductLikedRes> productLikedResList = EntityDtoMapper.INSTANCE.toProductLikedResList(productLikedList);

        return new ProductLikedListRes(productLikedResList);
    }

    @Override
    public boolean checkDuplicationByStickerId(Integer stickerId) {
        return productLikedRepository.findProductLikedBySticker_Id(stickerId).isPresent();
    }
}

package org.tattour.server.domain.user.provider.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.sticker.domain.Sticker;
import org.tattour.server.domain.user.domain.ProductLiked;
import org.tattour.server.domain.user.provider.ProductLikedProvider;
import org.tattour.server.domain.user.provider.dto.request.CheckDuplicationReqDto;
import org.tattour.server.domain.user.provider.dto.response.ProductLikedListRes;
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
    public ProductLiked getProductLikedByUserIdAndStickerId(Integer userId, Integer stickerId) {
        return productLikedRepository.findProductLikedByUser_IdAndSticker_Id(userId, stickerId)
                .orElseThrow(() -> new BusinessException(ErrorType.NOT_FOUND_RESOURCE));
    }

    //TODO : 리팩토링?
    @Override
    public ProductLikedListRes getLikedProductsByUserId(Integer userId) {
        List<ProductLiked> productLikedList = productLikedRepository.findAllByUser_Id(userId);
//        List<Sticker> stickerList = productLikedList.stream()
//                .map(ProductLiked::getSticker)
//                .collect(Collectors.toList());

        return new ProductLikedListRes(
                EntityDtoMapper.INSTANCE.toStickerLikedInfoList(productLikedList));
    }

    @Override
    public boolean checkDuplicationByStickerId(CheckDuplicationReqDto req) {
        return productLikedRepository.findProductLikedByUser_IdAndSticker_Id(req.getUserId(),
                req.getStickerId()).isPresent();
    }
}

package org.tattour.server.domain.user.provider;

import org.tattour.server.domain.user.domain.ProductLiked;
import org.tattour.server.domain.user.provider.dto.request.CheckDuplicationReqDto;
import org.tattour.server.domain.user.provider.dto.response.ProductLikedListRes;

public interface ProductLikedProvider {

    // productLiked 가져오기
    ProductLiked getProductLikedById(Integer id);

    ProductLiked getProductLikedByUserIdAndStickerId(Integer userId, Integer stickerId);

    // user Id로 좋아요한 타투 불러오기
    ProductLikedListRes getLikedProductsByUserId(Integer userId);

    // 중복된 상품인지 조회
    Boolean checkDuplicationByStickerId(CheckDuplicationReqDto req);
}

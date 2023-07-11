package org.tattour.server.domain.user.service;

import org.tattour.server.domain.user.service.dto.request.SaveProductLikedReq;
import org.tattour.server.domain.user.service.dto.response.ProductLikedListRes;

public interface ProductLikedService {
    // 좋아요 누른 타투 저장
    void saveProductLiked(SaveProductLikedReq req);

    // user Id로 좋아요한 타투 불러오기
    ProductLikedListRes getLikedProductsByUserId(Integer userId);
}

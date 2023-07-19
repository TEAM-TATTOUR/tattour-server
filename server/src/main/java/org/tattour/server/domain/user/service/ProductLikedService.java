package org.tattour.server.domain.user.service;

import org.tattour.server.domain.user.provider.dto.request.SaveProductLikedReq;
import org.tattour.server.domain.user.service.dto.request.DeleteProductLikedInfo;

public interface ProductLikedService {

    // 좋아요 누른 타투 저장
    void saveProductLiked(SaveProductLikedReq req);

    // 좋아요 누른 타투 삭제
    void deleteProductLiked(DeleteProductLikedInfo req);
}

package org.tattour.server.domain.user.service;


public interface ProductLikedService {

    // 좋아요 누른 타투 저장
    void saveProductLiked(int userId, int stickerId);

    // 좋아요 누른 타투 삭제
    void removeProductLiked(int userId, int stickerId);

    Boolean checkProductLikedExists(Integer userId, Integer stickerId);
}

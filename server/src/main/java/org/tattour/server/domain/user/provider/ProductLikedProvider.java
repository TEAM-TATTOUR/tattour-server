package org.tattour.server.domain.user.provider;

import java.util.List;
import org.tattour.server.domain.user.model.ProductLiked;

public interface ProductLikedProvider {

    // productLiked 가져오기
    ProductLiked readProductLikedById(Integer id);

    ProductLiked readProductLikedByUserIdAndStickerId(Integer userId, Integer stickerId);

    // user Id로 좋아요한 타투 불러오기
    List<ProductLiked> readLikedProductsByUserId(Integer userId);

    // 중복된 상품인지 조회
    Boolean checkDuplicationByStickerId(int userId, int stickerId);
}

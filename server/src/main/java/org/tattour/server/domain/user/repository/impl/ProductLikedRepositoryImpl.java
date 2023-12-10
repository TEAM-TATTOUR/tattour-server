package org.tattour.server.domain.user.repository.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.user.model.ProductLiked;

@Repository
public interface ProductLikedRepositoryImpl extends JpaRepository<ProductLiked, Integer> {

    @Override
    <S extends ProductLiked> S save(S entity);

    List<ProductLiked> findAllByUser_IdOrderByCreatedAtDesc(Integer userId);

    Optional<ProductLiked> findProductLikedByUser_IdAndSticker_Id(Integer userId,
                                                                  Integer stickerId);
}

package org.tattour.server.domain.user.repository.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.user.domain.ProductLiked;
@Repository
public interface ProductLikedRepositoryImpl extends JpaRepository<ProductLiked, Integer> {
    @Override
    <S extends ProductLiked> S save(S entity);

    List<ProductLiked> findAllByUser_Id(Integer userId);

    Optional<ProductLiked> findBySticker_IdAndUser_Id(Integer StickerId, Integer userId);

    Optional<ProductLiked> findProductLikedBySticker_Id(Integer stickerId);
}

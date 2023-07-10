package org.tattour.server.user.repository.impl;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.user.domain.ProductLiked;
@Repository
public interface ProductLikedRepositoryImpl extends JpaRepository<ProductLiked, Integer> {
    @Override
    <S extends ProductLiked> S save(S entity);

    List<ProductLiked> findAllByUser_Id(Integer userId);
}

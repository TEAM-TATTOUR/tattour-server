package org.tattour.server.domain.user.repository.impl;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.user.domain.User;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User, Integer> {

    @Override
    <S extends User> S save(S entity);

    @Override
    Optional<User> findById(Integer integer);

    Optional<User> findByKakaoId(Long kakaoId);
}

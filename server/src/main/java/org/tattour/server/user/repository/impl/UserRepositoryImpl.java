package org.tattour.server.user.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.user.domain.User;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User, Integer> {

}

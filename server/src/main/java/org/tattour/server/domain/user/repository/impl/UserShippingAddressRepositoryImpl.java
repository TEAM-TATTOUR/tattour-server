package org.tattour.server.domain.user.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.domain.user.model.UserShippingAddress;

@Repository
public interface UserShippingAddressRepositoryImpl extends JpaRepository<UserShippingAddress, Integer> {

}

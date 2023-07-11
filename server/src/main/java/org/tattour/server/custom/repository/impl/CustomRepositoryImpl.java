package org.tattour.server.custom.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tattour.server.custom.domain.Custom;

public interface CustomRepositoryImpl extends JpaRepository<Custom, Integer> {

}

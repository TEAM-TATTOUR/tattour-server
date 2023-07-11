package org.tattour.server.domain.custom.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tattour.server.domain.custom.domain.Custom;

public interface CustomRepositoryImpl extends JpaRepository<Custom, Integer> {

}

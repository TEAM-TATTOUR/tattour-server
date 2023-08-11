package org.tattour.server.domain.custom.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.repository.CustomRepository;

public interface CustomRepositoryImpl extends
		Repository<Custom, Integer>,
		CustomRepository {

}

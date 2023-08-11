package org.tattour.server.domain.custom.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.custom.domain.CustomStyle;
import org.tattour.server.domain.custom.repository.CustomStyleRepository;

public interface CustomStyleRepositoryImpl extends
		Repository<CustomStyle, Integer>,
		CustomStyleRepository {

}

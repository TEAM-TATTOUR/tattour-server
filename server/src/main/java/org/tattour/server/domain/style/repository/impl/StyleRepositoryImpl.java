package org.tattour.server.domain.style.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.style.domain.Style;
import org.tattour.server.domain.style.repository.StyleRepository;

public interface StyleRepositoryImpl extends
		Repository<Style, Integer>,
		StyleRepository {

}

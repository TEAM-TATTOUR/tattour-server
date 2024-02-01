package org.tattour.server.domain.magazine.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.magazine.model.Magazine;
import org.tattour.server.domain.magazine.repository.MagazineRepository;

public interface MagazineRepositoryImpl extends
		Repository<Magazine, Integer>,
		MagazineRepository {

}

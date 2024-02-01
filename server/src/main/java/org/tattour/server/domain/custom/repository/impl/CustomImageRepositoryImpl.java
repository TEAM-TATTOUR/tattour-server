package org.tattour.server.domain.custom.repository.impl;

import org.springframework.data.repository.Repository;
import org.tattour.server.domain.custom.model.CustomImage;
import org.tattour.server.domain.custom.repository.CustomImageRepository;

public interface CustomImageRepositoryImpl extends
		Repository<CustomImage, Integer>,
		CustomImageRepository {

}

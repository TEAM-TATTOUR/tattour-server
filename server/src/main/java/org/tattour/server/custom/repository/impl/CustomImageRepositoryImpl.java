package org.tattour.server.custom.repository.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tattour.server.custom.domain.CustomImage;

public interface CustomImageRepositoryImpl extends JpaRepository<CustomImage, Integer> {

}

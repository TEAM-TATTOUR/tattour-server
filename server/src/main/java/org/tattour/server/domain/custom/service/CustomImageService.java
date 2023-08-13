package org.tattour.server.domain.custom.service;

import java.util.List;
import org.tattour.server.domain.custom.domain.CustomImage;

public interface CustomImageService {

	CustomImage save(CustomImage customImage);
	List<CustomImage> saveAll(List<CustomImage> customImages);
}

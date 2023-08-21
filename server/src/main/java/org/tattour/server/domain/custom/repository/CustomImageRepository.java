package org.tattour.server.domain.custom.repository;

import java.util.List;
import org.tattour.server.domain.custom.domain.CustomImage;

public interface CustomImageRepository {

	CustomImage save(CustomImage customImage);

	List<CustomImage> saveAll(Iterable<CustomImage> customImages);
}

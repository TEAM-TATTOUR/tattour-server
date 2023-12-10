package org.tattour.server.domain.magazine.provider;

import java.util.List;
import org.tattour.server.domain.magazine.model.Magazine;

public interface MagazineProvider {

	Magazine getMagazineById(Integer id);

	List<Magazine> getAllMagazine();
}

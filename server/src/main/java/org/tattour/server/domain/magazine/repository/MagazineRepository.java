package org.tattour.server.domain.magazine.repository;

import java.util.List;
import java.util.Optional;
import org.tattour.server.domain.magazine.model.Magazine;

public interface MagazineRepository {

	Optional<Magazine> findById(Integer id);

	List<Magazine> findAll();
}

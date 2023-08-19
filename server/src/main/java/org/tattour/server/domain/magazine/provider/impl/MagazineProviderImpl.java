package org.tattour.server.domain.magazine.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tattour.server.domain.magazine.domain.Magazine;
import org.tattour.server.domain.magazine.exception.NotFoundMagazineException;
import org.tattour.server.domain.magazine.provider.MagazineProvider;
import org.tattour.server.domain.magazine.repository.MagazineRepository;

@Component
@RequiredArgsConstructor
public class MagazineProviderImpl implements MagazineProvider {

	private final MagazineRepository magazineRepository;

	@Override
	public Magazine getMagazineById(Integer id) {
		return magazineRepository.findById(id)
				.orElseThrow(NotFoundMagazineException::new);
	}

	@Override
	public List<Magazine> getAllMagazine() {
		return magazineRepository.findAll();
	}
}

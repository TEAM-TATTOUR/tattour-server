package org.tattour.server.domain.magazine.facade.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.tattour.server.domain.magazine.facade.MagazineFacade;
import org.tattour.server.domain.magazine.facade.dto.response.ReadMagazineRes;
import org.tattour.server.domain.magazine.facade.dto.response.ReadMagazineUrlRes;
import org.tattour.server.domain.magazine.provider.MagazineProvider;

@Slf4j
@Component
@RequiredArgsConstructor
public class MagazineFacadeImpl implements MagazineFacade {

	private final MagazineProvider magazineProvider;

	@Override
	public List<ReadMagazineRes> readMagazineList() {
		return magazineProvider
				.getAllMagazine()
				.stream()
				.map(ReadMagazineRes::from)
				.collect(Collectors.toList());
	}

	@Override
	public ReadMagazineUrlRes readMagazineUrlById(Integer id) {
		return ReadMagazineUrlRes.from(
				magazineProvider.getMagazineById(id));

	}
}

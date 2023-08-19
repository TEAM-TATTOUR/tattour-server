package org.tattour.server.domain.magazine.facade.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.tattour.server.domain.magazine.facade.MagazineFacade;
import org.tattour.server.domain.magazine.facade.dto.response.ReadMagazineListRes;
import org.tattour.server.domain.magazine.facade.dto.response.ReadMagazineUrlRes;
import org.tattour.server.domain.magazine.provider.MagazineProvider;

@Slf4j
@Component
@RequiredArgsConstructor
public class MagazineFacadeImpl implements MagazineFacade {

	private final MagazineProvider magazineProvider;

	@Override
	public ReadMagazineListRes readMagazineList() {
		return ReadMagazineListRes.from(
				magazineProvider.getAllMagazine());
	}

	@Override
	public ReadMagazineUrlRes readMagazineUrlById(Integer id) {
		return ReadMagazineUrlRes.from(
				magazineProvider.getMagazineById(id));

	}
}

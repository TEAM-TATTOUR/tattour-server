package org.tattour.server.domain.magazine.facade;

import org.tattour.server.domain.magazine.facade.dto.response.ReadMagazineListRes;
import org.tattour.server.domain.magazine.facade.dto.response.ReadMagazineUrlRes;

public interface MagazineFacade {

	ReadMagazineListRes readMagazineList();

	ReadMagazineUrlRes readMagazineUrlById(Integer id);
}

package org.tattour.server.domain.magazine.facade;

import java.util.List;
import org.tattour.server.domain.magazine.facade.dto.response.ReadMagazineRes;
import org.tattour.server.domain.magazine.facade.dto.response.ReadMagazineUrlRes;

public interface MagazineFacade {

	List<ReadMagazineRes> readMagazineList();

	ReadMagazineUrlRes readMagazineUrlById(Integer id);
}

package org.tattour.server.domain.custom.service;

import java.util.List;
import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.domain.CustomStyle;

public interface CustomStyleService {

	CustomStyle save(CustomStyle customStyle);

	List<CustomStyle> saveAll(List<CustomStyle> customStyles);

	List<CustomStyle> saveByCustomAndStyleIdList(Custom custom, List<Integer> styleIdList);
}

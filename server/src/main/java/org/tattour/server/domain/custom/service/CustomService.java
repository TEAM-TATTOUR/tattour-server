package org.tattour.server.domain.custom.service;

import org.tattour.server.domain.custom.service.dto.request.CreateCustomInfo;
import org.tattour.server.domain.custom.service.dto.response.CustomInfo;

public interface CustomService {

	CustomInfo createCustom(CreateCustomInfo customInfo, Integer userId);

}

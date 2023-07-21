package org.tattour.server.domain.custom.service;

import org.tattour.server.domain.custom.service.dto.request.UpdateCustomInfo;
import org.tattour.server.domain.custom.service.dto.response.CustomInfo;

public interface CustomService {

    Integer createCustom(Boolean haveDesign, Integer userId);

    CustomInfo updateCustom(UpdateCustomInfo updateCustomInfo);

    CustomInfo updateCustomProcess(UpdateCustomInfo updateCustomInfo);
}

package org.tattour.server.domain.custom.facade;


import org.tattour.server.domain.custom.facade.dto.request.UpdateCustomInfo;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomRes;

public interface CustomFacade {

    Integer createCustom(Boolean haveDesign, Integer userId);

    ReadCustomRes updateCustom(UpdateCustomInfo updateCustomInfo);

    ReadCustomRes updateCustomProcess(UpdateCustomInfo updateCustomInfo);
}

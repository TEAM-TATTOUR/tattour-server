package org.tattour.server.domain.custom.facade;


import org.tattour.server.domain.custom.facade.dto.request.UpdateCustomReq;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomRes;

public interface CustomFacade {

    Integer createCustom(Boolean haveDesign, Integer userId);

    ReadCustomRes updateCustom(UpdateCustomReq updateCustomReq);

    ReadCustomRes updateCustomProcess(UpdateCustomReq updateCustomReq);
}

package org.tattour.server.domain.custom.facade;


import org.tattour.server.domain.custom.facade.dto.request.UpdateCustomReq;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomRes;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomSummaryListRes;

public interface CustomFacade {

    Integer createCustom(Boolean haveDesign, Integer userId);

    ReadCustomRes readCustomById(Integer customId, Integer userId);

    ReadCustomSummaryListRes readCustomSummaryCompleteListByUserId(Integer userId);

    ReadCustomSummaryListRes readCustomSummaryInCompleteListByUserId(Integer userId);

    ReadCustomRes updateCustom(UpdateCustomReq updateCustomReq);

    ReadCustomRes updateCustomProcess(UpdateCustomReq updateCustomReq);
}

package org.tattour.server.domain.custom.facade;


import org.tattour.server.domain.custom.facade.dto.request.UpdateCustomReq;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomRes;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomSummaryListRes;

public interface CustomFacade {

    Integer createCustom(Boolean haveDesign, Integer userId);

    ReadCustomRes readCustomById(Integer customId, Integer userId);

    ReadCustomSummaryListRes getCustomSummaryCompleteListByUserId(Integer userId);

    ReadCustomSummaryListRes getCustomSummaryInCompleteListByUserId(Integer userId);

    ReadCustomSummaryListRes readCustomSummaryInfoAfterDateByUserId(int userId, String date);

    ReadCustomRes updateCustom(UpdateCustomReq updateCustomReq);

    ReadCustomRes updateCustomProcess(UpdateCustomReq updateCustomReq);
}

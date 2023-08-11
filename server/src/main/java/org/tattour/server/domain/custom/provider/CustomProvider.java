package org.tattour.server.domain.custom.provider;

import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.facade.dto.request.ReadCustomSummaryRes;
import org.tattour.server.domain.custom.facade.dto.response.CreateCustomSummaryListRes;
import org.tattour.server.domain.custom.facade.dto.response.ReadCustomSummaryListRes;

public interface CustomProvider {

    Custom getCustomById(Integer customId, Integer userId);

    // customInfo 가져오기
    CreateCustomSummaryListRes getCustomApplySummaryInfoList(ReadCustomSummaryRes req);

    ReadCustomSummaryListRes getCustomSummaryCompleteListByUserId(Integer userId);

    ReadCustomSummaryListRes getCustomSummaryInCompleteListByUserId(Integer userId);
}

package org.tattour.server.domain.custom.provider;

import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.service.dto.request.GetCustomSummaryInfo;
import org.tattour.server.domain.custom.service.dto.response.CustomApplySummaryInfoList;
import org.tattour.server.domain.custom.service.dto.response.CustomSummaryList;

public interface CustomProvider {

    Custom getCustomById(Integer customId, Integer userId);

    // customInfo 가져오기
    CustomApplySummaryInfoList getCustomApplySummaryInfoList(GetCustomSummaryInfo req);

    CustomSummaryList getCustomSummaryCompleteListByUserId(Integer userId);

    CustomSummaryList getCustomSummaryInCompleteListByUserId(Integer userId);
}

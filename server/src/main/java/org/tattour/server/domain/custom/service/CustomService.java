package org.tattour.server.domain.custom.service;

import org.tattour.server.domain.custom.domain.Custom;
import org.tattour.server.domain.custom.service.dto.request.UpdateCustomInfo;
import org.tattour.server.domain.custom.service.dto.response.CustomInfo;
import org.tattour.server.domain.custom.service.dto.response.CustomSummaryList;

public interface CustomService {

	Custom getCustomById(Integer customId, Integer userId);
	Integer createCustom(Boolean haveDesign, Integer userId);

	CustomInfo updateCustom(UpdateCustomInfo updateCustomInfo);

	CustomSummaryList getCustomSummaryCompleteListByUserId(Integer userId);

	CustomSummaryList getCustomSummaryInCompleteListByUserId(Integer userId);
}

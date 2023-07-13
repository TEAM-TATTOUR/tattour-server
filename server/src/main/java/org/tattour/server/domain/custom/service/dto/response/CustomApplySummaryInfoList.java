package org.tattour.server.domain.custom.service.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomApplySummaryInfoList {
    List<CustomApplySummaryInfo> customApplySummaryInfoList;
    public static CustomApplySummaryInfoList of(List<CustomApplySummaryInfo> customApplySummaryInfoList) {
        return new CustomApplySummaryInfoList(customApplySummaryInfoList);
    }

}

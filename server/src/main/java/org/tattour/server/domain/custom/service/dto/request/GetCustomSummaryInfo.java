package org.tattour.server.domain.custom.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetCustomSummaryInfo {
    private Integer userId;
    private String date;

    public static GetCustomSummaryInfo of(Integer userId, String date) {
           return new GetCustomSummaryInfo(userId, date);
    }
}

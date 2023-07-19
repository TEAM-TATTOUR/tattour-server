package org.tattour.server.domain.point.provider.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPointChargeRequestAfterDate {

    private int userId;
    private String date;

    public static GetPointChargeRequestAfterDate of(int userId, String date) {
        return new GetPointChargeRequestAfterDate(userId, date);
    }
}

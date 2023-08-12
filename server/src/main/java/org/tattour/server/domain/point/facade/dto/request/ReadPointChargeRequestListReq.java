package org.tattour.server.domain.point.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReadPointChargeRequestListReq {
    private Integer userId;
    private Boolean isCompleted;
    public static ReadPointChargeRequestListReq of(Integer userId, Boolean isCompleted){
        return new ReadPointChargeRequestListReq(userId, isCompleted);
    }
}

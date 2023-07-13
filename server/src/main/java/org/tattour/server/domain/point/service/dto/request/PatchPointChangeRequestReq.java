package org.tattour.server.domain.point.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchPointChangeRequestReq {
    private int id;
    private Boolean isDeposited;
    private Boolean  isAmountMatched;
    private Boolean isApproved;
    private Boolean isCompleted;
    public static PatchPointChangeRequestReq of(int id, Boolean isDeposited, Boolean isAmountMatched, Boolean isApproved, Boolean isCompleted) {
        return new PatchPointChangeRequestReq(id, isDeposited, isAmountMatched, isApproved, isCompleted);
    }
}

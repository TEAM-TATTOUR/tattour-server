package org.tattour.server.domain.point.provider.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPointChargeRequestRes {
    private int id;
    private int userId;
    private Integer chargeAmount;
    private Integer transferredAmount;
    private Boolean isDeposited;
    private Boolean isAmountMatched;
    private Boolean isCompleted;
    private String createdAt;
    private String lastUpdatedAt;
    private boolean state;
}

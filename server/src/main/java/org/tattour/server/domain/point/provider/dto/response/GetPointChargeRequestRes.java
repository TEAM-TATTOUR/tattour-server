package org.tattour.server.domain.point.provider.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "포인트 충전 요청 내역 Response")
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetPointChargeRequestRes {

    @Schema(description = "포인트 충전 요청 id")
    private int id;

    @Schema(description = "user Id")
    private int userId;

    @Schema(description = "충전 금액", example = "5000")
    private Integer chargeAmount;

    @Schema(description = "송금된 금액", example = "3000")
    private Integer transferredAmount;

    @Schema(description = "입금 여부", example = "true")
    private Boolean isDeposited;

    @Schema(description = "금액 일치 여부", example = "false")
    private Boolean isAmountMatched;

    @Schema(description = "", example = "")
    private Boolean isApproved;

    @Schema(description = "", example = "")
    private Boolean isCompleted;

    @Schema(description = "", example = "")
    private String createdAt;

    @Schema(description = "", example = "")
    private String lastUpdatedAt;

    @Schema(description = "", example = "")
    private Boolean state;
}

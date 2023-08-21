package org.tattour.server.domain.admin.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.tattour.server.global.config.annotations.DivisibleBy;

@Schema(description = "포인트 충전 요청 취소 Request")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CancelPointChargeRequestReq {

    @Schema(description = "포인트 충전 요청 id")
    @NotNull(message = "id is null")
    private Integer id;

    @Schema(description = "user id")
    @NotNull(message = "userId is null")
    private Integer userId;

    @Schema(description = "송금받은 금액")
    @DivisibleBy
    @NotNull(message = "transferredAmount is null")
    private Integer transferredAmount;

    @Schema(description = "취소 사유")
    @NotBlank(message = "reason is required")
    private String reason;

    public static CancelPointChargeRequestReq of(int id, int userId, Integer transferredAmount,
            String reason) {
        return new CancelPointChargeRequestReq(id, userId, transferredAmount, reason);
    }
}

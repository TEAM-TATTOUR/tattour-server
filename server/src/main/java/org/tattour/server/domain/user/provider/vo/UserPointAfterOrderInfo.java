package org.tattour.server.domain.user.provider.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Schema(description = "user 포인트 정보")
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserPointAfterOrderInfo {

    @Schema(description = "현재 user 포인트", example = "5000")
    private int userPoint;

    @Schema(description = "결과 포인트", example = "-7000")
    private int resultPoint;

    @Schema(description = "포인트 부족한지 여부", example = "true")
    private Boolean isLacked;

    public static UserPointAfterOrderInfo of(int userPoint, int resultPoint, Boolean isLacked) {
        return new UserPointAfterOrderInfo(userPoint, resultPoint, isLacked);
    }
}

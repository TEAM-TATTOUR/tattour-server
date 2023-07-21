package org.tattour.server.domain.user.controller.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "인증번호 검증 Response")
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetVerifyCodeRes {

    @Schema(description = "검증 여부")
    private Boolean isVerified;

    public static GetVerifyCodeRes of(Boolean isVerified) {
        return new GetVerifyCodeRes(isVerified);
    }
}

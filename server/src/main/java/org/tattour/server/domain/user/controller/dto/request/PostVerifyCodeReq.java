package org.tattour.server.domain.user.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostVerifyCodeReq {
    @Schema(description = "인증번호", example = "123456")
    @NotNull(message = "verificationCode is null")
    @Min(100000)
    @Max(999999)
    private Integer verificationCode;
}

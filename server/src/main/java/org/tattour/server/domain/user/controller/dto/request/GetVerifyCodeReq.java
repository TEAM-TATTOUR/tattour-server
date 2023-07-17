package org.tattour.server.domain.user.controller.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GetVerifyCodeReq {
    @NotNull(message = "userId is null")
    private Integer userId;

    @NotNull(message = "verificationCode is null")
    @Min(100000)
    @Max(999999)
    private Integer verificationCode;
}

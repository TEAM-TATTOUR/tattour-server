package org.tattour.server.infra.sms.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostSendCodeReq {
    @NotNull(message = "userId is null")
    private Integer userId;

    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber;
}

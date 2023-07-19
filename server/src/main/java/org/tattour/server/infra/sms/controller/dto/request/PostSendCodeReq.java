package org.tattour.server.infra.sms.controller.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostSendCodeReq {

    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber;
}

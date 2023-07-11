package org.tattour.server.domain.user.controller.dto.request;

import lombok.Getter;

@Getter
public class GetVerifyCodeReq {
    private Integer userId;
    private Integer verificationCode;
}

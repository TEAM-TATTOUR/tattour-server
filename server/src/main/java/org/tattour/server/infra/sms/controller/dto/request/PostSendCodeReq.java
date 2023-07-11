package org.tattour.server.infra.sms.controller.dto.request;

import lombok.Getter;

@Getter
public class PostSendCodeReq {
    private Integer userId;
    private String phoneNumber;
}

package org.tattour.server.infra.sms.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SendVerificationCodeReq {
    private Integer userId;
    private String recipientPhoneNumber;
}

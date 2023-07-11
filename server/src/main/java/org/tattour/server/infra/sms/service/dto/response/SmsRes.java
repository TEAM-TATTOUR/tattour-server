package org.tattour.server.infra.sms.service.dto.response;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class SmsRes {
    private String requestId;
    private LocalDateTime requestTime;
    private String statusCode;
    private String statusName;
}
package org.tattour.server.domain.user.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CompareVerificationCodeReq {
    private int userId;
    private int verificationCode;

    public static CompareVerificationCodeReq of(int userId, int verificationCode){
        return new CompareVerificationCodeReq(userId, verificationCode);
    }
}

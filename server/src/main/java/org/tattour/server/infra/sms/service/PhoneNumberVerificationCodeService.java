package org.tattour.server.infra.sms.service;

public interface PhoneNumberVerificationCodeService {

    // 인증번호 저장
    void saveVerificationCode(int code, Integer userId);
}

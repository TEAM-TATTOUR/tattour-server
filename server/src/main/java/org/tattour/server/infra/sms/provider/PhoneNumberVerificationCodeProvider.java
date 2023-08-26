package org.tattour.server.infra.sms.provider;

public interface PhoneNumberVerificationCodeProvider {

    // userId로 최신 인증번호 불러오기
    int getLatestValidVerificationCode(Integer userId);

    // 인증코드 비교하기
    boolean compareVerficationCode(Integer userId, Integer verificationCode);
}

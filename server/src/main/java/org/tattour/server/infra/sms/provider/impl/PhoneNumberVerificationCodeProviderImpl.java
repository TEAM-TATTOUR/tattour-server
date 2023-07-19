package org.tattour.server.infra.sms.provider.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.infra.sms.domain.PhoneNumberVerificationCode;
import org.tattour.server.infra.sms.provider.PhoneNumberVerificationCodeProvider;
import org.tattour.server.infra.sms.repository.PhoneNumberVerificationCodeRepositoryImpl;

@Service
@RequiredArgsConstructor
public class PhoneNumberVerificationCodeProviderImpl implements
        PhoneNumberVerificationCodeProvider {

    private final PhoneNumberVerificationCodeRepositoryImpl phoneNumberVerificationCodeRepository;

    @Override
    public int getLatestValidVerificationCode(Integer userId) {
        return phoneNumberVerificationCodeRepository
                .findAllVerificationCode(userId, Timestamp.valueOf(LocalDateTime.now()).toString())
                .stream()
                .findFirst()
                .map(PhoneNumberVerificationCode::getVerificationCode)
                .orElseThrow(() -> new BusinessException(
                        ErrorType.NOT_FOUND_VERIFICATION_CODE_EXCEPTION));
    }

    @Override
    public boolean compareVerficationCode(Integer userId, Integer verificationCode) {
        return verificationCode == getLatestValidVerificationCode(userId);
    }
}

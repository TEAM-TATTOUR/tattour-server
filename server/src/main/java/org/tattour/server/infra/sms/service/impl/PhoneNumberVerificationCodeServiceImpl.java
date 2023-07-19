package org.tattour.server.infra.sms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.infra.sms.domain.PhoneNumberVerificationCode;
import org.tattour.server.infra.sms.repository.PhoneNumberVerificationCodeRepositoryImpl;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.infra.sms.service.PhoneNumberVerificationCodeService;

@Service
@RequiredArgsConstructor
public class PhoneNumberVerificationCodeServiceImpl implements PhoneNumberVerificationCodeService {

    private final PhoneNumberVerificationCodeRepositoryImpl phoneNumberVerificationCodeRepository;
    private final UserProviderImpl userProvider;

    @Override
    @Transactional
    public void saveVerificationCode(int code, Integer userId) {
        phoneNumberVerificationCodeRepository.save(
                PhoneNumberVerificationCode.of(code, userProvider.getUserById(userId)));
    }
}

package org.tattour.server.infra.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tattour.server.infra.sms.domain.PhoneNumberVerificationCode;

@Repository
public interface PhoneNumberVerificationCodeRepositoryImpl extends JpaRepository<PhoneNumberVerificationCode, Integer> {

}

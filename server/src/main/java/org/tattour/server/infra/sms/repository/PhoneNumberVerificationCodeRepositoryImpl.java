package org.tattour.server.infra.sms.repository;

import feign.Param;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tattour.server.infra.sms.domain.PhoneNumberVerificationCode;

@Repository
public interface PhoneNumberVerificationCodeRepositoryImpl extends JpaRepository<PhoneNumberVerificationCode, Integer> {
    @Query("SELECT p "
            + "FROM PhoneNumberVerificationCode p "
            + "WHERE p.user.id = :userId "
            + "AND p.createdAt < :now "
            + "AND p.expirateAt > :now "
            + "ORDER BY p.createdAt DESC")
    List<PhoneNumberVerificationCode> findAllVerificationCode(
            @Param("userId") Integer userId, @Param("now") String now);
}

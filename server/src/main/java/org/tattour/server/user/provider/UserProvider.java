package org.tattour.server.user.provider;

public interface UserProvider {
    // 유저 중복 확인(이메일로)
    // 존재하면 userId / 존재하지 않으면 null return
    Integer checkDuplicateByEmail(String email);
}

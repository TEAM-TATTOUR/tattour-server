package org.tattour.server.user.provider;

import org.tattour.server.user.domain.User;
import org.tattour.server.user.service.dto.response.GetUserProfileRes;

public interface UserProvider {
    // 유저 가져오기
    User getUserByUserId(Integer userId);

    // 유저 프로필 가져오기
    GetUserProfileRes getUserProfile(Integer userId);

    // 이메일로 유저 중복 확인
    Integer checkDuplicateByEmail(String email);
}

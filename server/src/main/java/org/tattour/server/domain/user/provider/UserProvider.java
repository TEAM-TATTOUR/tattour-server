package org.tattour.server.domain.user.provider;

import org.tattour.server.domain.user.provider.dto.response.GetUserProfileRes;
import org.tattour.server.domain.user.domain.User;

public interface UserProvider {
    // 유저 가져오기
    User getUserById(Integer id);

    // 유저 프로필 가져오기
    GetUserProfileRes getUserProfile(Integer id);

    // 이메일로 유저 중복 확인
    Integer checkDuplicationByEmail(String email);

    // 포인트 부족 확인
    boolean isUserPointLack(Integer userId, Integer totalAmount);
}

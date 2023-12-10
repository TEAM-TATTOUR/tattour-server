package org.tattour.server.domain.user.provider;

import org.tattour.server.domain.user.model.User;
import org.tattour.server.domain.user.provider.vo.HomeUserInfo;

public interface UserProvider {

    // 유저 가져오기
    User readUserById(int id);

    User readUserByKakaoId(Long KakaoId);

    // 홈 유저 정보 가져오기
    HomeUserInfo readHomeUserInfo(User user);

    // 이메일로 유저 중복 확인
    boolean checkDuplicationByKakaoId(Long kakaoId);
}

package org.tattour.server.domain.user.provider;

import java.util.List;
import org.tattour.server.domain.order.provider.dto.request.CheckUserPointLackReqDto;
import org.tattour.server.domain.user.provider.dto.response.GetUserProfileRes;
import org.tattour.server.domain.user.domain.User;

public interface UserProvider {

    // 유저 가져오기
    User getUserById(Integer id);

    //TODO : 테스트용. 지우기
    // 모든 유저 가져오기
    List<User> getAllUsers();

    // 유저 프로필 가져오기
    GetUserProfileRes getUserProfile(Integer id);

    // 이메일로 유저 중복 확인
    Integer checkDuplicationByKakaoId(Long kakaoId);

    // 포인트 부족 확인
    boolean isUserPointLack(CheckUserPointLackReqDto req);
}

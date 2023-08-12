package org.tattour.server.domain.user.provider;

import java.util.List;
import org.tattour.server.domain.user.facade.dto.response.ReadUserProfileRes;
import org.tattour.server.domain.user.provider.vo.UserContactInfo;
import org.tattour.server.domain.user.provider.vo.UserPointAfterOrderInfo;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.provider.vo.UserProfileInfo;

public interface UserProvider {

    // 유저 가져오기
    User readUserById(int id);

    User readUserByKakaoId(Long KakaoId);

    //TODO : 테스트용. 지우기
    // 모든 유저 가져오기
    List<User> readAllUsers();

    // 유저 프로필 가져오기
    UserProfileInfo readUserProfile(int userId);

    // 이메일로 유저 중복 확인
    boolean checkDuplicationByKakaoId(Long kakaoId);

    // 포인트 부족 확인
    Boolean isUserPointLack(int userId, int totalAmount);

    // 주문 후 포인트 정보 가져오기
    UserPointAfterOrderInfo readUserPointAfterOrderInfo(int userId, int totalAmount);

    // 주문 후 남은 포인트 계산
    int calculateRestPointAfterOrder(int userPoint, int totalAmount);
}

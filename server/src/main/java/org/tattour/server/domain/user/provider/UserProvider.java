package org.tattour.server.domain.user.provider;

import java.util.List;
import org.tattour.server.domain.order.facade.dto.response.ReadUserOrderPointRes;
import org.tattour.server.domain.user.provider.dto.response.GetUserProfileRes;
import org.tattour.server.domain.user.domain.User;

public interface UserProvider {

    // 유저 가져오기
    User getUserById(int id);

    //TODO : 테스트용. 지우기
    // 모든 유저 가져오기
    List<User> getAllUsers();

    // 유저 프로필 가져오기
    GetUserProfileRes getUserProfile(int id);

    // 이메일로 유저 중복 확인
    Integer checkDuplicationByKakaoId(Long kakaoId);

    // 포인트 부족 확인
    Boolean isUserPointLack(int userId, int totalAmount);

    // 주문 후 포인트 정보 가져오기
    ReadUserOrderPointRes readUserPointAfterOrderInfo(int userId, int totalAmount);

    // 주문 후 남은 포인트 계산
    int calculateRestPointAfterOrder(int userPoint, int totalAmount);
}

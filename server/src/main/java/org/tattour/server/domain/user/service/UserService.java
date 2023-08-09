package org.tattour.server.domain.user.service;

import org.tattour.server.domain.user.service.dto.request.UpdateUserInfoReq;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.infra.socialLogin.client.kakao.domain.SocialPlatform;

public interface UserService {

    // 유저 저장
    void saveUser(User user);

    // userId로 user 가져오기
    User getUserByUserId(Integer userId);

    // 회원가입 시 이름, 전화번호 추가
    void updateUserInfo(UpdateUserInfoReq req);

    // 로그아웃
    void userLogout(Integer userId);

    // 유저 포인트 수정
    int updateUserPoint(Integer userId, Integer totalAmount);
}

package org.tattour.server.domain.user.service;

import org.tattour.server.domain.user.service.dto.request.UpdateUserPointReq;
import org.tattour.server.domain.user.service.dto.request.SaveUserReq;
import org.tattour.server.domain.user.service.dto.request.UpdateUserInfoReq;
import org.tattour.server.domain.user.domain.User;

public interface UserService {
    // 소셜 유저 생성
    User saveSocialUser(SaveUserReq req);

    // 유저 저장
    void saveUser(User user);

    User getUserByUserId(Integer userId);
    // 회원가입 시 이름, 전화번호 추가
    void updateUserInfo(UpdateUserInfoReq req);

    // 로그아웃
    void userLogout(Integer userId);

    // 유저 포인트 수정
    Integer updateUserPoint(UpdateUserPointReq req);
}

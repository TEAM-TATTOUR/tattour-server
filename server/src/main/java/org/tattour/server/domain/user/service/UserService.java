package org.tattour.server.domain.user.service;

import org.tattour.server.domain.user.domain.User;

public interface UserService {

    // 유저 저장
    void saveUser(User user);

    // userId로 user 가져오기
    User getUserByUserId(Integer userId);

    // 회원가입 시 이름, 전화번호 추가
    void updateUserProfile(int userId, String name, String phoneNumber);

    // 로그아웃
    void deleteSocialAccessToken(Integer userId);

    // 유저 포인트 수정
    int updateUserPoint(Integer userId, Integer totalAmount);
}

package org.tattour.server.user.service;

import org.tattour.server.user.domain.User;
import org.tattour.server.user.service.dto.request.AddUserInfoReq;
import org.tattour.server.user.service.dto.request.SaveUserReq;
import org.tattour.server.user.service.dto.request.SaveUserShippingAddrReq;
import org.tattour.server.user.service.dto.response.GetUserProfileRes;

public interface UserService {
    // get user
    User getUserByUserId(Integer userId);

    // user 생성
    User saveUser(SaveUserReq req);

    // 회원가입 시 이름, 전화번호 추가
    void addUserInfo(AddUserInfoReq req);

    // 유저 프로필 정보 가져오기
    GetUserProfileRes getUserProfile(Integer userId);

    // 배송지 등록
    void saveUserShippingAddr(SaveUserShippingAddrReq req);

    // 로그아웃
    void userLogout(Integer userId);
}

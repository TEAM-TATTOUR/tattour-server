package org.tattour.server.user.service;

import org.tattour.server.user.domain.User;
import org.tattour.server.user.service.dto.request.UpdateUserInfoReq;
import org.tattour.server.user.service.dto.request.SaveUserReq;
import org.tattour.server.user.service.dto.request.SaveUserShippingAddrReq;

public interface UserService {
    // 유저 생성
    User saveUser(SaveUserReq req);

    // 회원가입 시 이름, 전화번호 추가
    void updateUserInfo(UpdateUserInfoReq req);

    // 로그아웃
    void userLogout(Integer userId);

    // 배송지 등록
    void saveUserShippingAddr(SaveUserShippingAddrReq req);
}

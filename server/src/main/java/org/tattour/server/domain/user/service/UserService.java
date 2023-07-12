package org.tattour.server.domain.user.service;

import org.tattour.server.domain.user.service.dto.request.DeductUserPointReq;
import org.tattour.server.domain.user.service.dto.request.SaveUserReq;
import org.tattour.server.domain.user.service.dto.request.SaveUserShippingAddrReq;
import org.tattour.server.domain.user.service.dto.request.UpdateUserInfoReq;
import org.tattour.server.domain.user.domain.User;

public interface UserService {
    // 유저 생성
    User saveUser(SaveUserReq req);

    // 회원가입 시 이름, 전화번호 추가
    void updateUserInfo(UpdateUserInfoReq req);

    // 로그아웃
    void userLogout(Integer userId);

    // 유저 포인트 차감
    void userDeductPoint(DeductUserPointReq req);
}

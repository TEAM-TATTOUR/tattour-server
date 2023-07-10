package org.tattour.server.user.service;

import org.tattour.server.user.service.dto.request.AddUserInfoReq;
import org.tattour.server.user.service.dto.request.SaveProductLikedReq;
import org.tattour.server.user.service.dto.request.SaveUserReq;
import org.tattour.server.user.service.dto.request.SaveUserShippingAddrReq;
import org.tattour.server.user.service.dto.response.GetUserRes;
import org.tattour.server.user.service.dto.response.ProductLikedListRes;

public interface UserService {
    // user 생성
    void saveUser(SaveUserReq req);

    // 회원가입 시 이름, 전화번호 추가
    void addUserInfo(AddUserInfoReq req);

    // 유저 정보 가져오기
    GetUserRes getUser(Integer userId);

    // 좋아요 누른 타투 저장
    void createProductLiked(SaveProductLikedReq req);

    // 좋아요한 타투 불러오기
    ProductLikedListRes getUserLikedProducts(Integer userId);

    // 배송지 등록
    void saveUserShippingAddr(SaveUserShippingAddrReq req);

    // 로그아웃
    void userLogout(Integer userId);
}

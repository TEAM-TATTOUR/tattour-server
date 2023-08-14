package org.tattour.server.domain.user.facade;

import java.io.UnsupportedEncodingException;
import org.tattour.server.domain.user.controller.dto.response.PostLoginRes;
import org.tattour.server.domain.user.facade.dto.request.CompareVerificationCodeReq;
import org.tattour.server.domain.user.facade.dto.request.CreateLoginReq;
import org.tattour.server.domain.user.facade.dto.request.RemoveProductLikedReq;
import org.tattour.server.domain.user.facade.dto.request.SaveProductLikedReq;
import org.tattour.server.domain.user.facade.dto.request.UpdateUserProfileReq;
import org.tattour.server.domain.user.facade.dto.response.ReadUserProfileRes;
import org.tattour.server.domain.user.facade.dto.response.ProductLikedListRes;
import org.tattour.server.domain.user.facade.dto.request.SaveUserShippingAddrReq;

public interface UserFacade {
    // 회원가입, 로그인
    PostLoginRes signup(CreateLoginReq req) throws UnsupportedEncodingException;

    // 유저 로그아웃
    void userLogout(int userId);

    // 유저 프로필 정보 수정
    void updateUserProfile(UpdateUserProfileReq req);

    // 유저 프로필 가져오기
    ReadUserProfileRes readUserProfile(int userId);

    // 인증번호 검증
    Boolean verifyCode(CompareVerificationCodeReq req);

    // 좋아요 누른 타투 저장
    void saveProductLiked(SaveProductLikedReq req);

    // 좋아요 누른 타투 삭제
    void deleteProductLiked(RemoveProductLikedReq req);

    // 좋아요 누른 타투 불러오기
    ProductLikedListRes readProductLikedByUserId(int userId);

    // 배송지 등록
    void saveUserShippingAddr(SaveUserShippingAddrReq req);
}

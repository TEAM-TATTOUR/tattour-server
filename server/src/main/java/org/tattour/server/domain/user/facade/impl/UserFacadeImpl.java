package org.tattour.server.domain.user.facade.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.point.service.impl.CustomProviderImpl;
import org.tattour.server.domain.point.service.impl.PointServiceImpl;
import org.tattour.server.domain.user.controller.dto.response.LoginRes;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.facade.UserFacade;
import org.tattour.server.domain.user.facade.dto.request.CreateLoginReq;
import org.tattour.server.domain.user.provider.impl.ProductLikedProviderImpl;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.impl.ProductLikedServiceImpl;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.domain.user.service.impl.UserShippingAddressServiceImpl;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.infra.sms.provider.impl.PhoneNumberVerificationCodeProviderImpl;
import org.tattour.server.infra.socialLogin.client.kakao.domain.SocialPlatform;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialService;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialServiceProvider;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.request.GetSocialLoginReq;
import org.tattour.server.infra.socialLogin.client.kakao.service.vo.KakaoLoginInfo;

@Service
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {

    private final SocialServiceProvider socialServiceProvider;
    private final UserServiceImpl userService;
    private final UserProviderImpl userProvider;
    private final CustomProviderImpl customProvider;
    private final PhoneNumberVerificationCodeProviderImpl phoneNumberVerificationCodeProvider;
    private final ProductLikedServiceImpl productLikedService;
    private final ProductLikedProviderImpl productLikedProvider;
    private final UserShippingAddressServiceImpl userShippingAddressService;
    private final PointServiceImpl pointService;
    private final JwtService jwtService;

    @Override
    public LoginRes signup(CreateLoginReq req) {
        SocialService socialService = socialServiceProvider.getSocialService(
                req.getSocialPlatform());

        // 로그인
        KakaoLoginInfo kakaoLoginInfo =
                (KakaoLoginInfo) socialService.getSocialLoginResponse(
                        GetSocialLoginReq.of(req.getCode()));

        // 중복 확인
        boolean isUserExist = userProvider.checkDuplicationByKakaoId(
                kakaoLoginInfo.getSocialUserInfoRes().getId());

        User user = isUserExist
                // 존재시 불러오기
                ? userProvider.getUserByKakaoId(kakaoLoginInfo.getSocialUserInfoRes().getId())
                // 없으면 user 생성
                : User.of(
                        kakaoLoginInfo.getSocialUserInfoRes().getId(),
                        SocialPlatform.KAKAO);

        user.setSocialToken(
                kakaoLoginInfo.getSocialAccessTokenRes().getAccessToken(),
                kakaoLoginInfo.getSocialAccessTokenRes().getRefreshToken());

        userService.saveUser(user);

        boolean isUserSignUpCompleted = user.getName() != null;

        return LoginRes.of(
                user.getId(),
                jwtService.issuedToken(user.getId()),
                isUserSignUpCompleted);
    }
}

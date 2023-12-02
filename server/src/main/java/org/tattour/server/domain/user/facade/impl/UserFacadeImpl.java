package org.tattour.server.domain.user.facade.impl;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.user.controller.dto.response.PostLoginRes;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.domain.UserRole;
import org.tattour.server.domain.user.facade.UserFacade;
import org.tattour.server.domain.user.facade.dto.request.CompareVerificationCodeReq;
import org.tattour.server.domain.user.facade.dto.request.CreateLoginReq;
import org.tattour.server.domain.user.facade.dto.request.RemoveProductLikedReq;
import org.tattour.server.domain.user.facade.dto.request.SaveProductLikedReq;
import org.tattour.server.domain.user.facade.dto.request.SaveUserShippingAddrReq;
import org.tattour.server.domain.user.facade.dto.request.UpdateUserProfileReq;
import org.tattour.server.domain.user.facade.dto.response.ProductLikedListRes;
import org.tattour.server.domain.user.facade.dto.response.ReadUserProfileRes;
import org.tattour.server.domain.user.provider.ProductLikedProvider;
import org.tattour.server.domain.user.provider.UserProvider;
import org.tattour.server.domain.user.service.ProductLikedService;
import org.tattour.server.domain.user.service.UserShippingAddressService;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.global.util.EntityDtoMapper;
import org.tattour.server.infra.sms.provider.PhoneNumberVerificationCodeProvider;
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
    private final UserProvider userProvider;
    private final PhoneNumberVerificationCodeProvider phoneNumberVerificationCodeProvider;
    private final ProductLikedService productLikedService;
    private final ProductLikedProvider productLikedProvider;
    private final UserShippingAddressService userShippingAddressService;
    private final JwtService jwtService;

    @Override
    public PostLoginRes signup(CreateLoginReq req) {
        if (Objects.isNull(req.getOrigin())) {
            throw new BusinessException(ErrorType.NOT_FOUND_HEADER_ORIGIN);
        }
        System.out.println("Origin = " + req.getOrigin());

        SocialService socialService = socialServiceProvider.getSocialService(req.getSocialPlatform());

        KakaoLoginInfo kakaoLoginInfo = (KakaoLoginInfo) socialService
                .getSocialLoginResponse(GetSocialLoginReq.of(req.getCode(), req.getOrigin()));

        boolean isUserExist = userProvider
                .checkDuplicationByKakaoId(kakaoLoginInfo.getSocialUserInfoRes().getId());

        User user = isUserExist
                ? userProvider.readUserByKakaoId(kakaoLoginInfo.getSocialUserInfoRes().getId())
                : User.of(
                        kakaoLoginInfo.getSocialUserInfoRes().getId(),
                        SocialPlatform.KAKAO);

        user.setSocialToken(
                kakaoLoginInfo.getSocialAccessTokenRes().getAccessToken(),
                kakaoLoginInfo.getSocialAccessTokenRes().getRefreshToken());

        userService.saveUser(user);

        boolean isUserSignUpCompleted = user.getName() != null;

        return PostLoginRes.of(
                user.getId(),
                jwtService.issuedToken(user.getId(), UserRole.USER.toString()),
                isUserSignUpCompleted);
    }

    @Override
    public void userLogout(int userId) {
        userService.deleteSocialAccessToken(userId);
    }

    @Override
    public void updateUserProfile(UpdateUserProfileReq req) {
        User user = userProvider.readUserById(req.getUserId());
        userService.updateUserProfile(user, req.getName(), req.getPhoneNumber());
    }

    @Override
    public ReadUserProfileRes readUserProfile(int userId) {
        User user = userProvider.readUserById(userId);
        return ReadUserProfileRes.of(userProvider.readHomeUserInfo(user));
    }

    @Override
    public Boolean verifyCode(CompareVerificationCodeReq req) {
        return phoneNumberVerificationCodeProvider
                .compareVerficationCode(req.getUserId(), req.getVerificationCode());
    }

    @Override
    public void saveProductLiked(SaveProductLikedReq req) {
        // 중복 검사
        if (productLikedProvider.checkDuplicationByStickerId(req.getUserId(), req.getStickerId())) {
            throw new BusinessException(ErrorType.ALREADY_EXIST_PRODUCTLIKED_EXCEPTION);
        }

        productLikedService.saveProductLiked(req.getUserId(), req.getStickerId());
    }

    @Override
    public void deleteProductLiked(RemoveProductLikedReq req) {
        productLikedService.removeProductLiked(req.getUserId(), req.getStickerId());
    }

    @Override
    public ProductLikedListRes readProductLikedByUserId(int userId) {
        return new ProductLikedListRes(
                EntityDtoMapper.INSTANCE.toStickerLikedInfoList(
                        productLikedProvider.readLikedProductsByUserId(userId)));
    }

    @Override
    public void saveUserShippingAddr(SaveUserShippingAddrReq req) {
        userShippingAddressService.saveUserShippingAddr(
                req.getUserId(),
                req.getRecipientName(),
                req.getContact(),
                req.getMailingAddress(),
                req.getBaseAddress(),
                req.getDetailAddress()
        );
    }
}

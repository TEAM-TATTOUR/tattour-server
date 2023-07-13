package org.tattour.server.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.point.service.dto.request.SavePointChargeRequestReq;
import org.tattour.server.domain.point.service.dto.request.SaveUserPointLogReq;
import org.tattour.server.domain.point.service.impl.PointServiceImpl;
import org.tattour.server.domain.user.controller.dto.request.DeleteProductLikedReq;
import org.tattour.server.domain.user.controller.dto.request.PostPointChargeRequest;
import org.tattour.server.domain.user.controller.dto.request.PostProductLikedReq;
import org.tattour.server.domain.user.controller.dto.request.PostUserShippingAddrReq;
import org.tattour.server.domain.user.provider.dto.request.SaveProductLikedReq;
import org.tattour.server.domain.user.provider.impl.ProductLikedProviderImpl;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.dto.request.DeleteProductLikedInfo;
import org.tattour.server.domain.user.service.dto.request.SaveUserShippingAddrReq;
import org.tattour.server.domain.user.service.dto.request.UpdateUserPointReq;
import org.tattour.server.domain.user.service.impl.ProductLikedServiceImpl;
import org.tattour.server.domain.user.service.dto.request.UpdateUserInfoReq;
import org.tattour.server.domain.user.service.impl.UserShippingAddressServiceImpl;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.config.resolver.UserId;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;
import org.tattour.server.infra.sms.provider.impl.PhoneNumberVerificationCodeProviderImpl;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialService;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialServiceProvider;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.SocialLoginRequest;
import org.tattour.server.domain.user.controller.dto.request.GetVerifyCodeReq;
import org.tattour.server.domain.user.controller.dto.request.LoginReq;
import org.tattour.server.domain.user.controller.dto.request.PatchUserInfoReq;
import org.tattour.server.domain.user.controller.dto.response.GetVerifyCodeRes;
import org.tattour.server.domain.user.controller.dto.response.LoginRes;
import org.tattour.server.domain.user.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API Document")
public class UserController {

    private final SocialServiceProvider socialServiceProvider;
    private final UserServiceImpl userService;
    private final UserProviderImpl userProvider;
    private final PhoneNumberVerificationCodeProviderImpl phoneNumberVerificationCodeProvider;
    private final ProductLikedServiceImpl productLikedService;
    private final ProductLikedProviderImpl productLikedProvider;
    private final UserShippingAddressServiceImpl userShippingAddressService;
    private final PointServiceImpl pointService;
    private final JwtService jwtService;

    @Operation(summary = "소셜 회원가입/로그인")
    @PostMapping("/signup")
    public ResponseEntity<?> login(@RequestHeader("code") String code, @RequestBody LoginReq request) {
        SocialService socialService = socialServiceProvider.getSocialService(request.getSocialPlatform());

        // 로그인
        Integer userId = socialService.login(SocialLoginRequest.of(code));
        System.out.println(userId);

        // jwt 토큰 발급
        return ApiResponse.success(SuccessType.LOGIN_SUCCESS, LoginRes.of(userId, jwtService.issuedToken(userId)));
    }

    @Operation(summary = "user 이름, 전화번호 추가")
    @PatchMapping("/{userId}/profile")
    public ResponseEntity<?> updateUserProfile(
            @UserId Integer jwtUserId,
            @PathVariable("userId") Integer userId,
            @RequestBody PatchUserInfoReq req
    ){
        jwtService.compareJwtWithPathVar(jwtUserId, userId);

        userService.updateUserInfo(UpdateUserInfoReq.of(userId, req.getName(),
                req.getPhoneNumber()));

        return ApiResponse.success(SuccessType.UPDATE_SUCCESS);
    }

    @Operation(summary = "user profile 가져오기")
    @GetMapping("/{userId}/profile")
    public ResponseEntity<?> getUserProfile(
            @PathVariable("userId") Integer userId
    ){
        return ApiResponse.success(SuccessType.GET_SUCCESS, userProvider.getUserProfile(userId));
    }

    @Operation(summary = "user 로그아웃")
    @PatchMapping("/{userId}/logout")
    public ResponseEntity<?> userLogout(
            @UserId Integer jwtUserId,
            @PathVariable("userId") Integer userId
    ){
        jwtService.compareJwtWithPathVar(jwtUserId, userId);

        userService.userLogout(userId);
        return ApiResponse.success(SuccessType.LOGOUT_SUCCESS);
    }

    @Operation(summary = "인증번호 검증")
    @GetMapping("/phoneNum/verification")
    public ResponseEntity<?> verififyCode(
            @UserId Integer jwtUserId,
            @RequestBody GetVerifyCodeReq req){
        jwtService.compareJwtWithPathVar(jwtUserId, req.getUserId());

        if(phoneNumberVerificationCodeProvider.compareVerficationCode(req.getUserId(), req.getVerificationCode()))
            return ApiResponse.success(SuccessType.CODE_VERIFICATION_SUCCESS, GetVerifyCodeRes.of(true));
        else
            return ApiResponse.success(SuccessType.CODE_VALIDATION_FAIL, GetVerifyCodeRes.of(false));
    }

    @Operation(summary = "좋아요 누른 타투 저장")
    @PostMapping("/{userId}/productLiked/save")
    public ResponseEntity<?> saveProductLiked(
            @UserId Integer jwtUserId,
            @PathVariable("userId") Integer userId,
            @RequestBody PostProductLikedReq req
    ){
        jwtService.compareJwtWithPathVar(jwtUserId, userId);

        // 중복 검사
        if(productLikedProvider.checkDuplicationByStickerId(req.getStickerId()))
            throw new BusinessException(ErrorType.ALREADY_EXIST_PRODUCTLIKED_EXCEPTION);

        productLikedService.saveProductLiked(SaveProductLikedReq.of(userId, req.getStickerId()));

        return ApiResponse.success(SuccessType.CREATE_SUCCESS);
    }

    @Operation(summary = "좋아요 누른 타투 삭제")
    @DeleteMapping("/{userId}/productLiked/delete")
    public ResponseEntity<?> deleteProductLiked(
            @UserId Integer jwtUserId,
            @PathVariable("userId") Integer userId,
            @RequestBody DeleteProductLikedReq req
    ){
        jwtService.compareJwtWithPathVar(jwtUserId, userId);
        productLikedService.deleteProductLiked(DeleteProductLikedInfo.of(userId,
                req.getStickerId()));

        return ApiResponse.success(SuccessType.DELETE_SUCCESS);
    }

    @Operation(summary = "좋아요 누른 타투 불러오기")
    @GetMapping("/{userId}/productLiked/saved")
    public ResponseEntity<?> getProductLiked(
            @UserId Integer jwtUserId,
            @PathVariable("userId") Integer userId
    ){
        jwtService.compareJwtWithPathVar(jwtUserId, userId);

        return ApiResponse.success(SuccessType.GET_SUCCESS, productLikedProvider.getLikedProductsByUserId(userId));
    }

    @Operation(summary = "배송지 등록")
    @PostMapping("/{userId}/address")
    public ResponseEntity<?> createShippingAddr(
            @UserId Integer jwtUserId,
            @PathVariable("userId") Integer userId,
            @RequestBody PostUserShippingAddrReq req
    ){
        jwtService.compareJwtWithPathVar(jwtUserId, userId);
        userShippingAddressService.saveUserShippingAddr(
                SaveUserShippingAddrReq.of(
                        userId,
                        req.getRecipientName(),
                        req.getContact(),
                        req.getMailingAddress(),
                        req.getBaseAddress(),
                        req.getDetailAddress()));

        return ApiResponse.success(SuccessType.CREATE_SUCCESS);
    }

    @Operation(summary = "포인트 충전 신청")
    @PostMapping("/{userId}/point/charge")
    public ResponseEntity<?> createPointChargeRequest(
            @UserId Integer jwtUserId,
            @PathVariable("userId") Integer userId,
            @RequestBody @Valid PostPointChargeRequest req
    ){
        jwtService.compareJwtWithPathVar(jwtUserId, userId);

        pointService.savePointChargeRequest(
                SavePointChargeRequestReq.of(req.getUserId(), req.getChargeAmount()));
        Integer resultPoint = userService.updateUserPoint(UpdateUserPointReq.of(req.getUserId(), req.getChargeAmount()));
        pointService.savePointLog(
                SaveUserPointLogReq.of("포인트 충전 요청", req.getChargeAmount(), req.getUserId(), resultPoint));

        return ApiResponse.success(SuccessType.CREATE_POINT_CHARGE_REQUEST_SUCCESS);
    }

}

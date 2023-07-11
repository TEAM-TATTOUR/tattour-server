package org.tattour.server.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.service.dto.request.UpdateUserInfoReq;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.config.resolver.UserId;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;
import org.tattour.server.infra.sms.provider.PhoneNumberVerificationCodeProviderImpl;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialService;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialServiceProvider;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.SocialLoginRequest;
import org.tattour.server.domain.user.controller.dto.request.GetVerifyCodeReq;
import org.tattour.server.domain.user.controller.dto.request.LoginReq;
import org.tattour.server.domain.user.controller.dto.request.PatchUserInfoReq;
import org.tattour.server.domain.user.controller.dto.response.GetVerifyCodeRes;
import org.tattour.server.domain.user.controller.dto.response.LoginRes;
import org.tattour.server.domain.user.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API Document")
public class UserController {

    private final SocialServiceProvider socialServiceProvider;
    private final UserServiceImpl userService;
    private final UserProviderImpl userProvider;
    private final PhoneNumberVerificationCodeProviderImpl phoneNumberVerificationCodeProvider;
    private final JwtService jwtService;

    @Operation(summary = "소셜 회원가입/로그인")
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> updateUserProfile(
            @UserId Integer jwtUserId,
            @PathVariable("userId") Integer userId,
            @RequestBody PatchUserInfoReq patchUserInfoReq
    ){
        jwtService.compareJwtWithPathVar(jwtUserId, userId);

        userService.updateUserInfo(UpdateUserInfoReq.of(userId, patchUserInfoReq.getName(),
                patchUserInfoReq.getPhoneNumber()));

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
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
            @RequestBody GetVerifyCodeReq request){
        jwtService.compareJwtWithPathVar(jwtUserId, request.getUserId());

        if(phoneNumberVerificationCodeProvider.compareVerficationCode(request.getUserId(), request.getVerificationCode()))
            return ApiResponse.success(SuccessType.CODE_VERIFICATION_SUCCESS, GetVerifyCodeRes.of(true));
        else
            return ApiResponse.success(SuccessType.CODE_VALIDATION_FAIL, GetVerifyCodeRes.of(false));
    }

//    @Operation(summary = "좋아요 누른 타투 저장")
//    @GetMapping("/{userId}/products/save")
//
//    @Operation(summary = "좋아요한 타투 불러오기")
//    @GetMapping("/{userId}/products/saved")

}

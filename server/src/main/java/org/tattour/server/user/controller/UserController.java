package org.tattour.server.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.global.config.jwt.JwtService;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialService;
import org.tattour.server.infra.socialLogin.client.kakao.service.SocialServiceProvider;
import org.tattour.server.infra.socialLogin.client.kakao.service.dto.SocialLoginRequest;
import org.tattour.server.user.controller.dto.request.LoginReq;
import org.tattour.server.user.controller.dto.response.LoginRes;
import org.tattour.server.user.provider.impl.UserProviderImpl;
import org.tattour.server.user.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API Document")
public class UserController {
    private final SocialServiceProvider socialServiceProvider;
    private final UserServiceImpl userService;
    private final UserProviderImpl userProvider;
    private final JwtService jwtService;
    @PostMapping("/signup")
    @Operation(summary = "소셜 회원가입/로그인")
    public ResponseEntity<?> login(@RequestHeader("code") String code, @RequestBody LoginReq request) {
        SocialService socialService = socialServiceProvider.getSocialService(request.getSocialPlatform());

        // 로그인
        Integer userId = socialService.login(SocialLoginRequest.of(code));
        System.out.println(userId);

        // jwt 토큰 발급
        return ApiResponse.success(SuccessType.LOGIN_SUCCESS, LoginRes.of(userId, jwtService.issuedToken(userId)));
    }

}

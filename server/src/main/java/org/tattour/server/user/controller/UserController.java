package org.tattour.server.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;
import org.tattour.server.user.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API Document")
public class UserController {
    private final UserService userService;

//    @PostMapping("/signup")
//    @Operation(summary = "카카오 소셜 회원가입/로그인")
//    public ResponseEntity<?> signup() {
//        // 회원가입인지 로그인인지 판단
//        // 이메일 찾기
//
//        // 회원가입
//        // 유저 행 추가
//
//        // 로그인
//        // jwt 토큰 발급
//
//        return ApiResponse.success(SuccessType.LOGIN_SUCCESS);
//    }
}

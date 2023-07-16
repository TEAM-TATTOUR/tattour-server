package org.tattour.server.infra.sms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.global.config.resolver.UserId;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;
import org.tattour.server.infra.sms.controller.dto.request.PostSendCodeReq;
import org.tattour.server.infra.sms.service.impl.SmsServiceImpl;
import org.tattour.server.infra.sms.service.dto.request.SendVerificationCodeReq;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
@Tag(name = "Sms", description = "Sms API Document")
public class SmsController {
    private final SmsServiceImpl smsService;
    @Operation(summary = "전화번호 인증번호 보내기")
    @PostMapping("/send/verificationCode")
    public ResponseEntity<?> sendVerificationCode(
        @Parameter(hidden = true) @UserId Integer userId,
            @RequestBody PostSendCodeReq request
    ) throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
        smsService.sendVerificationCode(new SendVerificationCodeReq(request.getUserId(), request.getPhoneNumber()));

        return ApiResponse.success(SuccessType.CREATE_SUCCESS);
    }
}

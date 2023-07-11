package org.tattour.server.infra.sms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.tattour.server.global.dto.ApiResponse;
import org.tattour.server.global.dto.SuccessType;
import org.tattour.server.infra.sms.controller.dto.request.PostSendCodeReq;
import org.tattour.server.infra.sms.service.SmsServiceImpl;
import org.tattour.server.infra.sms.service.dto.request.SendVerificationCodeReq;

@RestController
@RequestMapping("/sms")
@RequiredArgsConstructor
public class SmsController {
    private final SmsServiceImpl smsService;
    @Operation(summary = "전화번호 인증번호 보내기")
    @PostMapping("/send/verificationCode")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> sendVerificationCode(@RequestBody PostSendCodeReq request)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
        smsService.sendVerificationCode(new SendVerificationCodeReq(request.getUserId(), request.getPhoneNumber()));

        return ApiResponse.success(SuccessType.CREATE_SUCCESS);
    }
}

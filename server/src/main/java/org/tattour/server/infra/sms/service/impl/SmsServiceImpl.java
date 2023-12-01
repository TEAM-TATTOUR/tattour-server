package org.tattour.server.infra.sms.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tattour.server.global.util.RandomNumberGenerator;
import org.tattour.server.infra.sms.service.dto.request.MessageDto;
import org.tattour.server.infra.sms.service.dto.request.SendVerificationCodeReq;
import org.tattour.server.infra.sms.service.dto.request.SmsReq;
import org.tattour.server.infra.sms.service.dto.response.SmsRes;
import org.tattour.server.infra.sms.service.impl.PhoneNumberVerificationCodeServiceImpl;

@Service
@Transactional
@RequiredArgsConstructor
public class SmsServiceImpl {

    @Value("${sms.serviceId}")
    private String serviceId;
    @Value("${sms.accessKey}")
    private String accessKey;
    @Value("${sms.secretKey}")
    private String secretKey;
    @Value("${sms.phone-number}")
    private String phoneNumber;

    private final RandomNumberGenerator randomNumberGenerator;
    private final PhoneNumberVerificationCodeServiceImpl phoneNumberVerificationCodeService;

    // 인증번호 sms 전송
    @Transactional
    public SmsRes sendVerificationCode(SendVerificationCodeReq request)
            throws JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {

        Long time = System.currentTimeMillis();
        List<MessageDto> messages = new ArrayList<>();
        messages.add(new MessageDto(request.getRecipientPhoneNumber()));

        // 6자리 랜덤 번호 생성
        int verificationCode = randomNumberGenerator.generateRandomNumber();
        String content = "[Tattour 본인확인] 인증번호 [" + verificationCode + "]를 입력해주세요.";

        System.out.println(content);

        // 저장
        phoneNumberVerificationCodeService.saveVerificationCode(verificationCode,
                request.getUserId());

        SmsReq smsReq = SmsReq.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(phoneNumber)
                .content(content)
                .messages(messages)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsReq);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", this.accessKey);
        String sig = makeSignature(time); //암호화
        headers.set("x-ncp-apigw-signature-v2", sig);

        HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        SmsRes smsRes = restTemplate.postForObject(
                new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + this.serviceId
                        + "/messages"), body, SmsRes.class);

        return smsRes;
    }

    @Transactional
    public SmsRes sendSms(String recipientPhoneNumber, String content)
            throws JsonProcessingException, UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
        Long time = System.currentTimeMillis();
        List<MessageDto> messages = new ArrayList<>();
        messages.add(new MessageDto(recipientPhoneNumber, content));

        SmsReq smsReq = SmsReq.builder()
                .type("SMS")
                .contentType("COMM")
                .countryCode("82")
                .from(phoneNumber)
                .content("내용")
                .messages(messages)
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(smsReq);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", this.accessKey);
        String sig = makeSignature(time); //암호화
        headers.set("x-ncp-apigw-signature-v2", sig);

        HttpEntity<String> body = new HttpEntity<>(jsonBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        SmsRes smsRes = restTemplate.postForObject(
                new URI("https://sens.apigw.ntruss.com/sms/v2/services/" + this.serviceId
                        + "/messages"), body, SmsRes.class);

        return smsRes;
    }

    public String makeSignature(Long time)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/" + this.serviceId + "/messages";
        String timestamp = time.toString();
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }
}

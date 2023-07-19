package org.tattour.server.domain.user.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;

@Schema(description = "user 배송지 등록 Request")
@Getter
public class PostUserShippingAddrReq {

    @Schema(description = "수신인 이름", example = "recipientName")
    @NotBlank(message = "recipientName is required")
    @Size(max = 20, message = "recipientName is max 20")
    private String recipientName;

    @Schema(description = "수신인 연락처", example = "010-1234-5678")
    @NotBlank(message = "contact is required")
    @Pattern(regexp = "\\d+", message = "숫자만 입력해주세요.")
    private String contact;

    @Schema(description = "우편주소", example = "03963")
    @NotBlank(message = "mailingAddress is required")
    @Size(max = 10, message = "mailingAddress is max 10")
    private String mailingAddress;

    @Schema(description = "기본주소", example = "서울특별시 마포구 방울내로 90 (망원동)")
    @NotBlank(message = "baseAddress is required")
    @Size(max = 100, message = "baseAddress is max 100")
    private String baseAddress;

    @Schema(description = "상세주소", example = "2층")
    @Size(max = 50, message = "detailAddress is max 50")
    private String detailAddress;
}

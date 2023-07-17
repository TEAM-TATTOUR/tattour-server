package org.tattour.server.domain.user.controller.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostUserShippingAddrReq {
    @NotBlank(message = "recipientName is required")
    @Size(max = 20, message = "recipientName is max 20")
    private String recipientName;

    @NotBlank(message = "contact is required")
    @Pattern(regexp = "\\d+", message = "숫자만 입력해주세요.")
    private String contact;

    @NotBlank(message = "mailingAddress is required")
    @Size(max = 10, message = "mailingAddress is max 10")
    private String mailingAddress;

    @NotBlank(message = "baseAddress is required")
    @Size(max = 100, message = "baseAddress is max 100")
    private String baseAddress;

    @Size(max = 50, message = "detailAddress is max 50")
    private String detailAddress;
}

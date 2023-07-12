package org.tattour.server.domain.user.controller.dto.request;

import lombok.Getter;

@Getter
public class PostUserShippingAddrReq {
    private String recipientName;
    private String contact;
    private String mailingAddress;
    private String baseAddress;
    private String detailAddress;
}

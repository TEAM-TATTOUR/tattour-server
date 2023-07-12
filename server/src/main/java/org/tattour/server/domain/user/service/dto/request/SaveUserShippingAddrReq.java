package org.tattour.server.domain.user.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveUserShippingAddrReq {
    private Integer userId;
    private String recipientName;
    private String contact;
    private String mailingAddress;
    private String baseAddress;
    private String detailAddress;

    public static SaveUserShippingAddrReq of(Integer userId, String recipientName, String contact,
            String mailingAddress, String baseAddress, String detailAddress){
        return new SaveUserShippingAddrReq(userId, recipientName, contact, mailingAddress, baseAddress, detailAddress);
    }
}

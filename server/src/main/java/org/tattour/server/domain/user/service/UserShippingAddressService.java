package org.tattour.server.domain.user.service;


public interface UserShippingAddressService {

    // 배송지 등록
    void saveUserShippingAddr(
            int userId, String recipientName, String contact, String mailingAddress,
            String baseAddress, String detailAddress);
}

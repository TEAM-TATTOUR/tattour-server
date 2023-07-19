package org.tattour.server.domain.user.service;

import org.tattour.server.domain.user.service.dto.request.SaveUserShippingAddrReq;

public interface UserShippingAddressService {

    // 배송지 등록
    void saveUserShippingAddr(SaveUserShippingAddrReq request);
}

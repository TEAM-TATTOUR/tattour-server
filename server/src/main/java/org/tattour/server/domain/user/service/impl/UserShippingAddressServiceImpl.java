package org.tattour.server.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.domain.UserShippingAddress;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.repository.impl.UserShippingAddressRepositoryImpl;
import org.tattour.server.domain.user.service.UserShippingAddressService;
import org.tattour.server.domain.user.service.dto.request.SaveUserShippingAddrReq;

@Service
@RequiredArgsConstructor
public class UserShippingAddressServiceImpl implements UserShippingAddressService {
    private final UserShippingAddressRepositoryImpl userShippingAddressRepository;
    private final UserProviderImpl userProvider;

    @Override
    public void saveUserShippingAddr(SaveUserShippingAddrReq request) {
        User user = userProvider.getUserById(request.getUserId());

        userShippingAddressRepository.save(
                UserShippingAddress.of(
                        request.getRecipientName(),
                        request.getContact(),
                        request.getMailingAddress(),
                        request.getBaseAddress(),
                        request.getDetailAddress(),
                        user
                ));
    }
}

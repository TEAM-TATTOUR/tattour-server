package org.tattour.server.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.domain.UserShippingAddress;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.repository.impl.UserShippingAddressRepositoryImpl;
import org.tattour.server.domain.user.service.UserShippingAddressService;

@Service
@RequiredArgsConstructor
public class UserShippingAddressServiceImpl implements UserShippingAddressService {

    private final UserShippingAddressRepositoryImpl userShippingAddressRepository;
    private final UserProviderImpl userProvider;

    @Override
    public void saveUserShippingAddr(
            int userId, String recipientName, String contact, String mailingAddress,
            String baseAddress, String detailAddress) {
        User user = userProvider.readUserById(userId);

        userShippingAddressRepository.save(
                UserShippingAddress.of(
                        recipientName,
                        contact,
                        mailingAddress,
                        baseAddress,
                        detailAddress,
                        user));
    }
}

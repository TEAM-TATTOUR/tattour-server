package org.tattour.server.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.user.exception.NotFoundUserException;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.repository.impl.UserRepositoryImpl;
import org.tattour.server.domain.user.service.UserService;
import org.tattour.server.domain.user.service.dto.request.SaveUserReq;
import org.tattour.server.domain.user.service.dto.request.SaveUserShippingAddrReq;
import org.tattour.server.domain.user.service.dto.request.UpdateUserInfoReq;
import org.tattour.server.domain.user.domain.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository;
    private final UserProviderImpl userProvider;

    @Override
    @Transactional
    public User saveUser(SaveUserReq request) {
        return userRepository.save(User.of(request));
    }

    @Override
    public User getUserByUserId(Integer userId) {
        return userRepository.findById(userId)
            .orElseThrow(NotFoundUserException::new);
    }

    @Override
    @Transactional
    public void updateUserInfo(UpdateUserInfoReq request) {
        User user = userProvider.getUserById(request.getUserId());
        user.setUserInfo(request);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void userLogout(Integer userId) {
        User user = userProvider.getUserById(userId);
        user.deleteToken();
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void saveUserShippingAddr(SaveUserShippingAddrReq request) {

    }
}

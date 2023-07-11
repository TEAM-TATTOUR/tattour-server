package org.tattour.server.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.user.domain.User;
import org.tattour.server.user.provider.impl.UserProviderImpl;
import org.tattour.server.user.repository.impl.UserRepositoryImpl;
import org.tattour.server.user.service.dto.request.UpdateUserInfoReq;
import org.tattour.server.user.service.dto.request.SaveUserReq;
import org.tattour.server.user.service.dto.request.SaveUserShippingAddrReq;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepositoryImpl userRepository;
    private final UserProviderImpl userProvider;

    @Override
    @Transactional
    public User saveUser(SaveUserReq req) {
        return userRepository.save(User.of(req));
    }

    @Override
    @Transactional
    public void updateUserInfo(UpdateUserInfoReq req) {
        User user = userProvider.getUserByUserId(req.getUserId());
        user.setUserInfo(req);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void userLogout(Integer userId) {
        User user = userProvider.getUserByUserId(userId);
        user.deleteToken();
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void saveUserShippingAddr(SaveUserShippingAddrReq req) {

    }
}

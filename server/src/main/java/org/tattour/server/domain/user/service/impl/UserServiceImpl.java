package org.tattour.server.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.user.exception.NotFoundUserException;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.repository.impl.UserRepositoryImpl;
import org.tattour.server.domain.user.service.UserService;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.global.exception.BusinessException;
import org.tattour.server.global.exception.ErrorType;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;
    private final UserProviderImpl userProvider;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User readUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
    }

    @Override
    @Transactional
    public void updateUserProfile(User user, String name, String phoneNumber) {
        user.setUserInfo(name, phoneNumber);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteSocialAccessToken(Integer userId) {
        User user = userProvider.readUserById(userId);
        user.deleteToken();
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserPoint(User user, Integer totalAmount) {
        int resultPoint = user.getPoint() + totalAmount;
        user.setUserPoint(resultPoint);
        userRepository.save(user);
    }
}

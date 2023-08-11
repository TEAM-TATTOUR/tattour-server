package org.tattour.server.domain.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tattour.server.domain.user.exception.NotFoundUserException;
import org.tattour.server.domain.user.provider.impl.UserProviderImpl;
import org.tattour.server.domain.user.repository.impl.UserRepositoryImpl;
import org.tattour.server.domain.user.service.UserService;
import org.tattour.server.domain.user.domain.User;

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
    public User getUserByUserId(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
    }

    @Override
    @Transactional
    public void updateUserProfile(int userId, String name, String phoneNumber) {
        User user = userProvider.readUserById(userId);
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
    public int updateUserPoint(Integer userId, Integer totalAmount) {
        User user = userProvider.readUserById(userId);
        int resultPoint = user.getPoint() + totalAmount;
        user.setUserPoint(resultPoint);

        userRepository.save(user);

        return resultPoint;
    }
}

package org.tattour.server.domain.user.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.user.repository.impl.UserRepositoryImpl;
import org.tattour.server.domain.user.service.dto.response.GetUserProfileRes;
import org.tattour.server.global.util.EntityDtoMapper;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.exception.NotFoundUserException;
import org.tattour.server.domain.user.provider.UserProvider;

@Service
@RequiredArgsConstructor
public class UserProviderImpl implements UserProvider {

    private final UserRepositoryImpl userRepository;

    @Override
    public User getUserByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
        return user;
    }

    @Override
    public GetUserProfileRes getUserProfile(Integer userId) {
        return EntityDtoMapper.INSTANCE.toGetUserProfileRes(getUserByUserId(userId));
    }

    @Override
    public Integer checkDuplicateByEmail(String email) {
        return userRepository.findByEmail(email).map(User::getId).orElse(null);
    }
}

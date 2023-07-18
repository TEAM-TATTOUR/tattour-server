package org.tattour.server.domain.user.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.order.provider.dto.request.CheckUserPointLackReqDto;
import org.tattour.server.domain.user.repository.impl.UserRepositoryImpl;
import org.tattour.server.domain.user.provider.dto.response.GetUserProfileRes;
import org.tattour.server.global.util.EntityDtoMapper;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.exception.NotFoundUserException;
import org.tattour.server.domain.user.provider.UserProvider;

@Service
@RequiredArgsConstructor
public class UserProviderImpl implements UserProvider {

    private final UserRepositoryImpl userRepository;

    @Override
    public User getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(NotFoundUserException::new);
        return user;
    }

    @Override
    public GetUserProfileRes getUserProfile(Integer id) {
        return EntityDtoMapper.INSTANCE.toGetUserProfileRes(getUserById(id));
    }

    @Override
    public Integer checkDuplicationByKakaoId(Long kakaoId) {
        return userRepository.findByKakaoId(kakaoId).map(User::getId).orElse(null);
    }

    @Override
    public boolean isUserPointLack(CheckUserPointLackReqDto req) {
        return req.getTotalAmount() > getUserById(req.getUserId()).getPoint();
    }
}

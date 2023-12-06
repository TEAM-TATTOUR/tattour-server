package org.tattour.server.domain.user.provider.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.exception.NotFoundUserException;
import org.tattour.server.domain.user.provider.UserProvider;
import org.tattour.server.domain.user.provider.vo.HomeUserInfo;
import org.tattour.server.domain.user.repository.impl.UserRepositoryImpl;
import org.tattour.server.global.util.EntityDtoMapper;

@Service
@RequiredArgsConstructor
public class UserProviderImpl implements UserProvider {

    private final UserRepositoryImpl userRepository;

    @Override
    public User readUserById(int id) {
        return userRepository.findById(id)
                .orElseThrow(NotFoundUserException::new);
    }

    @Override
    public User readUserByKakaoId(Long kakaoId) {
        return userRepository.findBySocialId(kakaoId)
                .orElseThrow(NotFoundUserException::new);
    }

    @Override
    public HomeUserInfo readHomeUserInfo(User user) {
        return EntityDtoMapper.INSTANCE.toHomeUserInfo(user);
    }

    @Override
    public boolean checkDuplicationByKakaoId(Long kakaoId) {
        return userRepository.findBySocialId(kakaoId).isPresent();
    }
}

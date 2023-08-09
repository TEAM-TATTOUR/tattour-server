package org.tattour.server.domain.user.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.domain.user.provider.vo.UserPointAfterOrderInfo;
import org.tattour.server.domain.user.provider.vo.UserProfileInfo;
import org.tattour.server.domain.user.repository.impl.UserRepositoryImpl;
import org.tattour.server.domain.user.facade.dto.response.ReadUserProfileRes;
import org.tattour.server.global.util.EntityDtoMapper;
import org.tattour.server.domain.user.domain.User;
import org.tattour.server.domain.user.exception.NotFoundUserException;
import org.tattour.server.domain.user.provider.UserProvider;

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
        return userRepository.findByKakaoId(kakaoId)
                .orElseThrow(NotFoundUserException::new);
    }

    // TODO : 테스트용. 지우기
    @Override
    public List<User> readAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserProfileInfo readUserProfile(int userId) {
        return EntityDtoMapper.INSTANCE.toUserProfileInfo(readUserById(userId));
    }

    @Override
    public boolean checkDuplicationByKakaoId(Long kakaoId) {
//        return userRepository.findByKakaoId(kakaoId).map(User::getId).orElse(null);
        return userRepository.findByKakaoId(kakaoId).isPresent();
    }

    @Override
    public Boolean isUserPointLack(int userId, int totalAmount) {
        return totalAmount > readUserById(userId).getPoint();
    }

    @Override
    public UserPointAfterOrderInfo readUserPointAfterOrderInfo(int userId, int totalAmount) {
        User user = readUserById(userId);

        int userPoint = user.getPoint();
        int resultPoint = calculateRestPointAfterOrder(userPoint, totalAmount);
        boolean isLacked = resultPoint < 0;

        return UserPointAfterOrderInfo.of(userPoint, resultPoint, isLacked);
    }

    @Override
    public int calculateRestPointAfterOrder(int userPoint, int totalAmount) {
        return userPoint - totalAmount;
    }
}

package org.tattour.server.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.global.util.EntityDtoMapper;
import org.tattour.server.user.domain.User;
import org.tattour.server.user.exception.NotFoundUserException;
import org.tattour.server.user.repository.impl.UserRepositoryImpl;
import org.tattour.server.user.service.dto.request.AddUserInfoReq;
import org.tattour.server.user.service.dto.request.SaveUserReq;
import org.tattour.server.user.service.dto.request.SaveUserShippingAddrReq;
import org.tattour.server.user.service.dto.response.GetUserProfileRes;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepositoryImpl userRepository;

    @Override
    public User getUserByUserId(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundUserException::new);
        return user;
    }

    @Override
    public void saveUser(SaveUserReq req) {
        userRepository.save(User.of(req));
    }

    @Override
    public void addUserInfo(AddUserInfoReq req) {
        User user = getUserByUserId(req.getUserId());
        user.setUserInfo(req);
    }

    @Override
    public GetUserProfileRes getUserProfile(Integer userId) {
        User user = getUserByUserId(userId);

        return EntityDtoMapper.INSTANCE.toGetUserProfileRes(user);
    }

    @Override
    public void saveUserShippingAddr(SaveUserShippingAddrReq req) {

    }

    @Override
    public void userLogout(Integer userId) {

    }
}

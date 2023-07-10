package org.tattour.server.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tattour.server.user.repository.impl.UserRepositoryImpl;
import org.tattour.server.user.service.dto.request.AddUserInfoReq;
import org.tattour.server.user.service.dto.request.SaveProductLikedReq;
import org.tattour.server.user.service.dto.request.SaveUserReq;
import org.tattour.server.user.service.dto.request.SaveUserShippingAddrReq;
import org.tattour.server.user.service.dto.response.GetUserRes;
import org.tattour.server.user.service.dto.response.ProductLikedListRes;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepositoryImpl userRepository;

    @Override
    public void saveUser(SaveUserReq req) {

    }

    @Override
    public void addUserInfo(AddUserInfoReq req) {

    }

    @Override
    public GetUserRes getUser(Integer userId) {
        return null;
    }

    @Override
    public void createProductLiked(SaveProductLikedReq req) {

    }

    @Override
    public ProductLikedListRes getUserLikedProducts(Integer userId) {
        return null;
    }

    @Override
    public void saveUserShippingAddr(SaveUserShippingAddrReq req) {

    }

    @Override
    public void userLogout(Integer userId) {

    }
}

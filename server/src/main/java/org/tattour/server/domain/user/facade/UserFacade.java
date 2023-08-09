package org.tattour.server.domain.user.facade;

import org.tattour.server.domain.user.controller.dto.response.LoginRes;
import org.tattour.server.domain.user.facade.dto.request.CreateLoginReq;

public interface UserFacade {
    // 회원가입, 로그인
    LoginRes signup(CreateLoginReq req);


}

package org.tattour.server.user.controller.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRes {
    private Integer userId;
    private String accessToken;

    public LoginRes(Integer userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }
    public static LoginRes of(Integer userId, String accessToken){
        return new LoginRes(userId, accessToken);
    }
}

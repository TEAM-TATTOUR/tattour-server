package org.tattour.server.user.service.dto.request;

import lombok.Getter;

@Getter
public class SaveUserReq {
    private String email;
    private String loginType;
}

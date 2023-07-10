package org.tattour.server.user.service.dto.request;

import lombok.Getter;

@Getter
public class AddUserInfoReq {
    private Integer userId;
    private String name;
    private String phoneNumber;
}

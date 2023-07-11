package org.tattour.server.user.service.dto.request;

import lombok.Getter;

@Getter
public class UpdateUserInfoReq {
    private Integer userId;
    private String name;
    private String phoneNumber;

    private UpdateUserInfoReq(Integer userId, String name, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public static UpdateUserInfoReq of(Integer userId, String name, String phoneNumber){
        return new UpdateUserInfoReq(userId, name, phoneNumber);
    }
}

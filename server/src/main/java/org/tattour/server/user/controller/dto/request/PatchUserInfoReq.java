package org.tattour.server.user.controller.dto.request;

import lombok.Getter;

@Getter
public class PatchUserInfoReq {
    private String name;
    private String phoneNumber;
}

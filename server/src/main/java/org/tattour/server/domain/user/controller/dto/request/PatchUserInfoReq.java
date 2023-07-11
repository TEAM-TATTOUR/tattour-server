package org.tattour.server.domain.user.controller.dto.request;

import lombok.Getter;

@Getter
public class PatchUserInfoReq {
    private String name;
    private String phoneNumber;
}

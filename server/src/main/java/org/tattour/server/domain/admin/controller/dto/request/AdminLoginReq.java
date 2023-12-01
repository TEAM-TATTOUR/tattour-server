package org.tattour.server.domain.admin.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "로그인 request")
@Getter
public class AdminLoginReq {

    private String userName;
    private String password;
}

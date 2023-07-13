package org.tattour.server.domain.user.provider.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserInfoDto {
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
}

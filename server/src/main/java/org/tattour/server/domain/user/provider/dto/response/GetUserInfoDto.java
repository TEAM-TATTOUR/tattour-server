package org.tattour.server.domain.user.provider.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GetUserInfoDto {
    private Integer id;
    private String name;
    private String email;
    private String phoneNumber;
}

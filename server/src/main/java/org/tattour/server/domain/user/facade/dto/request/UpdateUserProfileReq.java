package org.tattour.server.domain.user.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateUserProfileReq {

    private int userId;
    private String name;
    private String phoneNumber;

    public static UpdateUserProfileReq of(Integer userId, String name, String phoneNumber) {
        return new UpdateUserProfileReq(userId, name, phoneNumber);
    }
}

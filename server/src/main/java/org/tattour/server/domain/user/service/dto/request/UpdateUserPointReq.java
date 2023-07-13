package org.tattour.server.domain.user.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdateUserPointReq {
    private int userId;
    private int amount;

    public static UpdateUserPointReq of(int userId, int amount){
        return new UpdateUserPointReq(userId, amount);
    }
}

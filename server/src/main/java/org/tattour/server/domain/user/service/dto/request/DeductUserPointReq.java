package org.tattour.server.domain.user.service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeductUserPointReq {
    private int userId;
    private int amount;

    public static DeductUserPointReq of(int userId, int amount){
        return new DeductUserPointReq(userId, amount);
    }
}

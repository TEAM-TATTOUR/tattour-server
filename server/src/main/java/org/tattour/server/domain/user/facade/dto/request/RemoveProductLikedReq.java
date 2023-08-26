package org.tattour.server.domain.user.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RemoveProductLikedReq {
    private int userId;
    private int stickerId;

    public static RemoveProductLikedReq of(int userId, int stickerId){
        return new RemoveProductLikedReq(userId, stickerId);
    }
}

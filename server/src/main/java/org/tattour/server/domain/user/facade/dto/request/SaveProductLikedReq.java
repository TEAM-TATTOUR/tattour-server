package org.tattour.server.domain.user.facade.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveProductLikedReq {

    private Integer userId;
    private Integer StickerId;

    public static SaveProductLikedReq of(Integer userId, Integer stickerId) {
        return new SaveProductLikedReq(userId, stickerId);
    }
}

package org.tattour.server.domain.user.provider.dto.request;

import lombok.Getter;

@Getter
public class SaveProductLikedReq {
    private Integer userId;
    private Integer StickerId;

    public SaveProductLikedReq(Integer userId, Integer stickerId) {
        this.userId = userId;
        StickerId = stickerId;
    }

    public static SaveProductLikedReq of(Integer userId, Integer stickerId){
        return new SaveProductLikedReq(userId, stickerId);
    }
}

package org.tattour.server.domain.user.service.dto.request;

import lombok.Getter;

//TODO : 이름 생각해보기
@Getter
public class DeleteProductLikedInfo {

    private Integer userId;
    private Integer stickerId;

    private DeleteProductLikedInfo(Integer userId, Integer stickerId) {
        this.userId = userId;
        this.stickerId = stickerId;
    }

    public static DeleteProductLikedInfo of(Integer userId, Integer stickerId) {
        return new DeleteProductLikedInfo(userId, stickerId);
    }
}

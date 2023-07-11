package org.tattour.server.domain.user.provider.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductLikedRes {
    private Integer id;
    private Integer userId;
    //TODO : sticker 정보도 주는 방식으로 변경하기
    private Integer stickerId;
    private String createdAt;
}

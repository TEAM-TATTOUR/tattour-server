package org.tattour.server.domain.user.service.dto.request;

import lombok.Getter;

@Getter
public class SaveProductLikedReq {
    private Integer userId;
    private Integer StickerId;
}

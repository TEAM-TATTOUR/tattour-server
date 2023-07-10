package org.tattour.server.user.service.dto.request;

import lombok.Getter;

@Getter
public class SaveProductLikedReq {
    private Integer userId;
    private Integer StickerId;
}

package org.tattour.server.domain.user.controller.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Schema(description = "좋아요 누른 상품 저장")
@Getter
public class PostProductLikedReq {

    @Schema(description = "타투 스티커 Id")
    @NotNull(message = "stickerId is null")
    private Integer stickerId;
}

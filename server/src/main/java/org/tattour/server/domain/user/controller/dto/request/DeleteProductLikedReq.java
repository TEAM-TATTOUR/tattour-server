package org.tattour.server.domain.user.controller.dto.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class DeleteProductLikedReq {
    @NotNull(message = "stickerId is null")
    private Integer stickerId;
}

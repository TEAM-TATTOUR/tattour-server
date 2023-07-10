package org.tattour.server.user.service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.tattour.server.sticker.domain.Sticker;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductLikedRes {
    private Integer id;
    private Integer userId;
    private Sticker sticker;
    private String createdAt;
}

package org.tattour.server.domain.user.service.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductLikedListRes {
    List<ProductLikedRes> stickersliked;
}

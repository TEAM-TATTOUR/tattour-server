package org.tattour.server.domain.user.facade.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.tattour.server.domain.sticker.provider.vo.StickerLikedInfo;

@Schema(description = "좋아요한 타투 스티커 리스트 Response")
@Getter
@Setter
@AllArgsConstructor
public class ProductLikedListRes {

    @Schema(description = "좋아요한 타투 스티커 리스트")
    List<StickerLikedInfo> stickersliked;
}

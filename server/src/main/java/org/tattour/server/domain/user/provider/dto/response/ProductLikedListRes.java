package org.tattour.server.domain.user.provider.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tattour.server.domain.sticker.provider.dto.response.StickerLikedInfo;

@Getter
@Setter
@AllArgsConstructor
public class ProductLikedListRes {
    List<StickerLikedInfo> stickersliked;
}

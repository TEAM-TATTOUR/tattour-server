package org.tattour.server.domain.custom.controller.dto.response;

import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.tattour.server.domain.custom.service.dto.response.CustomInfo;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UpdateCustomRes {

    private String mainImageUrl;
    private List<String> imageUrls;
    private List<String> themes;
    private List<String> styles;
    private Boolean isColored;
    private String size;
    private Integer count;
    private String name;
    private String description;
    private String demand;

    public static UpdateCustomRes of(CustomInfo customInfo) {
        return UpdateCustomRes.builder()
                .mainImageUrl(customInfo.getMainImageUrl())
                .imageUrls(customInfo.getImages())
                .themes(customInfo.getThemes())
                .styles(customInfo.getStyles())
                .isColored(customInfo.getIsColored())
                .size(customInfo.getSize())
                .count(customInfo.getCount())
                .name(customInfo.getName())
                .demand(customInfo.getDemand())
                .demand(customInfo.getDemand())
                .build();
    }

}
